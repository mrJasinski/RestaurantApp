package com.RestaurantAppV2.order.service;

import com.RestaurantAppV2.order.Order;
import com.RestaurantAppV2.order.OrderStatus;
import com.RestaurantAppV2.order.dto.OrderDTO;
import com.RestaurantAppV2.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    String notifyKitchenAboutNewOrder(OrderDTO order)
    {
        return String.format("Do kolejki dodano zamówienie numer %s - %s", order.getNumber(), order.getMeal());
    }

    Order createOrder(OrderDTO toSave)
    {
        return this.repository.save(toSave.toOrder());
    }

    void saveOrderStatusData(OrderDTO order, OrderStatus status)
    {
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        this.repository.updateOrderStatus(order.getNumber(), status.name());
    }

    void changeOrderStatus(OrderDTO order)
    {
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
}
