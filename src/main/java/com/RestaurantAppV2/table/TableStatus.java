package com.RestaurantAppV2.table;

public enum TableStatus
{
    FREE("Wolny"),
    TAKEN("Zajęty"),
    RESERVED("Zarezerwowany");

    private final String name;

    TableStatus(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
