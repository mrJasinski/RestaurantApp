package com.RestaurantAppV2.order.service;

import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.order.Order;
import com.RestaurantAppV2.order.OrderStatus;
import com.RestaurantAppV2.order.dto.OrderDTO;
import com.RestaurantAppV2.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService
{
    private final OrderRepository repository;

    public OrderService(OrderRepository repository)
    {
        this.repository = repository;
    }

    String notifyWaiterAboutOrder(OrderDTO order)
    {
        return String.format("Zamówienie %s dla stolika %s jest gotowe do odbioru z kuchni", order.getNumber(), order.getBill().getTable().getName());
    }

    Order createOrder(OrderDTO toSave)
    {
        return this.repository.save(toSave.toOrder());
    }

    void saveOrderStatusData(OrderDTO order, OrderStatus status)
    {
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        this.repository.updateOrderStatusByNumber(order.getNumber(), status.name());
    }

    void changeOrderStatus(OrderDTO order)
    {
//        czy to się przyda?
        if (!(order.getStatus().equals(OrderStatus.SERVED)))
        {
            var status = order.getStatus();

            switch (status)
            {
                case ORDERED -> status = OrderStatus.IN_PREPARATION;
                case IN_PREPARATION -> status = OrderStatus.TO_PICKUP;
                case TO_PICKUP -> status = OrderStatus.SERVED;
            }

            saveOrderStatusData(order, status);
        }
    }

    long getLatestOrderNumber()
    {
        var result = 0L;

        if (this.repository.findLatestOrderNumber().isPresent())
            result = this.repository.findLatestOrderNumber().get();

        return result;
    }

    void updateOrderStatus(OrderDTO order, OrderStatus status)
    {
        order.setStatus(OrderStatus.TO_PICKUP);

        updateOrderStatusInDbByNumber(order.getNumber(), status);
    }

    void updateOrderStatusInDbByNumber(String number, OrderStatus status)
    {
        this.repository.updateOrderStatusByNumber(number, status.name());
    }

    void orderMeals(List<MealDTO> meals, BillDTO bill)
    {
//        utworzenie zamówień na posiłki i powiadomień na kuchnię
        meals.forEach(m ->
        {
            var order = new OrderDTO(String.valueOf(getLatestOrderNumber() + 1), m, bill);

            createOrder(order);

            notifyKitchenAboutNewOrder(order);
        });
    }

    String notifyKitchenAboutNewOrder(OrderDTO order)
    {
        return String.format("Nowe zamówienie (#%s) - %s!", order.getNumber(), order.getMeal().getName());
    }

    void startPreparingOrder(OrderDTO order)
    {
        updateOrderStatus(order, OrderStatus.IN_PREPARATION);
    }

    void endPreparingOrder(OrderDTO order)
    {
        updateOrderStatus(order, OrderStatus.TO_PICKUP);

        notifyWaiterAboutPreparedOrder(order);
    }

    String notifyWaiterAboutPreparedOrder(OrderDTO order)
    {
        return String.format("Zamówienie na stolik %s - %s jest gotowe do odbioru!", order.getBill().getTable().getName(), order.getMeal().getName());
    }

    void finishOrder(OrderDTO order)
    {
        updateOrderStatus(order, OrderStatus.SERVED);
    }
}
