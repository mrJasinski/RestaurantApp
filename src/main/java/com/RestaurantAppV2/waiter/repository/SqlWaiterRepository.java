package com.RestaurantAppV2.waiter.repository;

import com.RestaurantAppV2.waiter.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SqlWaiterRepository extends WaiterRepository, JpaRepository<Waiter, Integer>
{
    @Override
    @Query(value = "SELECT * FROM waiters WHERE id = (SELECT waiter_id FROM bills GROUP BY waiter_id HAVING MIN(COUNT(*)))", nativeQuery = true)
    Set<Waiter> findWaiterWithLowestBillsNumber();
}
