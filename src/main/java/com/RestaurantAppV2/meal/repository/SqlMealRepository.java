package com.RestaurantAppV2.meal.repository;

import com.RestaurantAppV2.meal.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlMealRepository extends MealRepository, JpaRepository<Meal, Integer>
{

}
