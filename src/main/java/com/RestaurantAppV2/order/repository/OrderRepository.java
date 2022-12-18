package com.RestaurantAppV2.order.repository;

import com.RestaurantAppV2.order.Order;

public interface OrderRepository
{
    Order save(Order entity);

    void updateOrderStatus(String number, String status);
}
