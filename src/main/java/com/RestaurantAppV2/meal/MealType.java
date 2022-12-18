package com.RestaurantAppV2.meal;

public enum MealType
{
    SOUP("Zupa"),
    MAIN_COURSE("Danie główne"),
    COLD_BEVERAGE("Napój zimny"),
    HOT_BEVERAGE("Napój gorący");

    private final String name;

    MealType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
