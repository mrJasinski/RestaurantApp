package com.RestaurantAppV2.waiter.repository;

import com.RestaurantAppV2.waiter.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

@Repository
public interface SqlWaiterRepository extends WaiterRepository, JpaRepository<Waiter, Integer>
{
    @Override
    @Query(value = "SELECT * FROM waiters WHERE id = (SELECT waiter_id FROM bills GROUP BY waiter_id HAVING MIN(COUNT(*)))", nativeQuery = true)
    Set<Waiter> findWaiterWithLowestBillsNumber();

//TODO having powoduje problem

    @Override
    @Query(value = "FROM Waiter w JOIN Bill b WHERE b.closedAt = :date GROUP BY w ORDER BY MAX(SUM(b.amount))")
    Set<Waiter> findWaiterWithHighestBillsSumByDate(LocalDate date);

    @Override
    @Query(value = "SELECT w, COUNT(w) FROM Waiter w JOIN Bill b WHERE b.closedAt = :date GROUP BY w")
    HashMap<Waiter, Integer> findWaitersWithBillsNumbersByDate(LocalDate date);
}
