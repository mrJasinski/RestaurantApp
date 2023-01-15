package com.RestaurantAppV2.meal.service;

import com.RestaurantAppV2.meal.Meal;
import com.RestaurantAppV2.meal.MealType;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.meal.repository.MealRepository;
import org.junit.jupiter.api.Test;

import java.lang.constant.DynamicCallSiteDesc;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MealServiceTest
{
//    HashMap<MealType, List<MealDTO>> getMenuAsDto()
//    {
//        var result = new HashMap<MealType, List<MealDTO>>();
//
//        getMealTypesList().forEach(t -> result.put(t, getMealsByTypeAsDto(t)));
//
//        return  result;
//    }
    @Test
    void getMenuAsDto_shouldReturnMapOfMealsWithTypeAsKey()
    {
//        given
        var meal1 = new Meal();
        var meal2 = new Meal();
        var meal3 = new Meal();
        var meal4 = new Meal();
        var meal5 = new Meal();

        var meals1 = List.of(meal1, meal2);
        var meals2 = List.of(meal3, meal4, meal5);

        var mockMealRepo = mock(MealRepository.class);
        when(mockMealRepo.findMealsByType(MealType.SOUP.name())).thenReturn(meals1);
        when(mockMealRepo.findMealsByType(MealType.MAIN_COURSE.name())).thenReturn(meals2);

//        system under test
        var toTest = new MealService(mockMealRepo);

//        when
        var result = toTest.getMenuAsDto();

//        then
        assertEquals(2, result.size());
        assertEquals(2, result.get(MealType.SOUP).size());
        assertEquals(3, result.get(MealType.MAIN_COURSE).size());

    }

//    schemat do kopiowania
//        given
//        system under test
//        when
//        then
}