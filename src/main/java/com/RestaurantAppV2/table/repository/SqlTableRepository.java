package com.RestaurantAppV2.table.repository;

import com.RestaurantAppV2.table.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlTableRepository extends TableRepository, JpaRepository<RestaurantTable, Integer>
{
}
