package com.RestaurantAppV2.order.repository;

import com.RestaurantAppV2.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SqlOrderRepository extends OrderRepository, JpaRepository<Order, Integer>
{
    @Override
    @Query(value = "UPDATE Order o SET o.status = :status WHERE o.number = :number")
    void updateOrderStatusByNumber(String number, String status);

    @Override
    @Query(value = "SELECT MAX(o.number) FROM Order o")
    Optional<Long> findLatestOrderNumber();
}
