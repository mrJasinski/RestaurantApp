package com.RestaurantAppV2.table.repository;

import com.RestaurantAppV2.table.RestaurantTable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TableRepository
{
    List<RestaurantTable> getFreeTablesByGivenHourAndSeats(LocalDateTime time, Integer seats);
    Optional<RestaurantTable> findByName(String name);

    void updateTableStatusByName(String name, String status);

    Optional<Integer> findIdByName(String name);

    RestaurantTable save(RestaurantTable entity);
}
