package com.RestaurantAppV2.order.dto;

import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.order.Order;
import com.RestaurantAppV2.order.OrderStatus;

import java.time.LocalDateTime;

public class OrderDTO
{
    private String number;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private LocalDateTime updatedAt;
    private MealDTO meal;
    private BillDTO bill;

    public Order toOrder()
    {
//        TODO
        return null;
    }

    public String getNumber()
    {
        return this.number;
    }

    public LocalDateTime getCreatedAt()
    {
        return this.createdAt;
    }

    public OrderStatus getStatus()
    {
        return this.status;
    }

    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt()
    {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public MealDTO getMeal()
    {
        return this.meal;
    }

    public BillDTO getBill()
    {
        return this.bill;
    }
}
