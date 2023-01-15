package com.RestaurantAppV2.meal.repository;

import com.RestaurantAppV2.meal.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Repository
public interface SqlMealRepository extends MealRepository, JpaRepository<Meal, Integer>
{
    @Override
    @Query(value = "FROM Meal m JOIN Bill b WHERE b.closedAt = :date")
    List<Meal> findServedMealsByDay(LocalDate date);

    @Override
    @Query(value = "SELECT m, COUNT(m) FROM Meal m JOIN Bill b WHERE b.closedAt = :date GROUP BY m")
    HashMap<Meal, Integer> findServedMealsWithAmountByDay(LocalDate date);
}
