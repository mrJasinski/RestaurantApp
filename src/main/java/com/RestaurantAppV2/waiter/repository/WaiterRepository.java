package com.RestaurantAppV2.waiter.repository;

import com.RestaurantAppV2.waiter.Waiter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public interface WaiterRepository
{
    Set<Waiter> findWaiterWithLowestBillsNumber();

    Waiter save(Waiter entity);

    Set<Waiter> findWaiterWithHighestBillsSumByDate(LocalDate date);

    HashMap<Waiter, Integer> findWaitersWithBillsNumbersByDate(LocalDate date);
}
