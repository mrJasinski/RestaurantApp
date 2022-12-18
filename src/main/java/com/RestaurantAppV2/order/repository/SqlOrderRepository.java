package com.RestaurantAppV2.order.repository;

import com.RestaurantAppV2.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlOrderRepository extends OrderRepository, JpaRepository<Order, Integer>
{

}
