package com.RestaurantAppV2.order.repository;

import com.RestaurantAppV2.order.Order;

import java.util.Optional;

public interface OrderRepository
{
    Order save(Order entity);

    void updateOrderStatusByNumber(String number, String status);

    Optional<Long> findLatestOrderNumber();
}
