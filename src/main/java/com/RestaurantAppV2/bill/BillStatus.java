package com.RestaurantAppV2.bill;

public enum BillStatus
{
    OPEN("Otwarty"),
    CLOSED("Zamknięty"),
    PAID("Opłacony");

    private final String name;

    BillStatus(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
