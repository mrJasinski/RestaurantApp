package com.RestaurantAppV2.meal.repository;

import com.RestaurantAppV2.meal.Meal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface MealRepository
{
    Meal save(Meal entity);

    List<Meal> findServedMealsByDay(LocalDate date);

    HashMap<Meal, Integer> findServedMealsWithAmountByDay(LocalDate date);

    List<Meal> findMealsByType(String type);
}
