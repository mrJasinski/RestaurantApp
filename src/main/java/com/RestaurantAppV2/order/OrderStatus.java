package com.RestaurantAppV2.order;

public enum OrderStatus
{
    ORDERED("Zamówione"),
    IN_PREPARATION("W przygotowaniu"),
    TO_PICKUP("Do odbioru"),
    SERVED("Wydane");

    private final String name;

    OrderStatus(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
