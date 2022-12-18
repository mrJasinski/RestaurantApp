package com.RestaurantAppV2.meal.service;

import com.RestaurantAppV2.meal.Meal;
import com.RestaurantAppV2.meal.MealType;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.meal.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService
{
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository)
    {
        this.mealRepository = mealRepository;
    }

    Meal createMeal(MealDTO toSave)
    {
        return this.mealRepository.save(toSave.toMeal());
    }

    List<MealDTO> getMealsByTypeAsDto(MealType type)
    {
        return this.mealRepository.findMealsByType(type.name()).stream().map(Meal::toDto).collect(Collectors.toList());
    }

    List<MealType> getMealTypesList()
    {
        return List.of(MealType.values());
    }

    HashMap<MealType, List<MealDTO>> getMenuAsDto()
    {
        var result = new HashMap<MealType, List<MealDTO>>();

        getMealTypesList().forEach(t -> result.put(t, getMealsByTypeAsDto(t)));

        return  result;
    }

    List<MealDTO> getServedMealsByDayAsDto(LocalDate date)
    {
        return this.mealRepository.findServedMealsByDay(date).stream().map(Meal::toDto).collect(Collectors.toList());
    }

    HashMap<MealDTO, Integer> getServedMealsWithAmountByDayAsDto(LocalDate date)
    {
        var result = new HashMap<MealDTO, Integer>();

        this.mealRepository.findServedMealsWithAmountByDay(date).forEach((k, v) -> result.put(k.toDto(), v));

        return result;
    }
}
