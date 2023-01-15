package com.RestaurantAppV2.meal.dto;

import com.RestaurantAppV2.bill.Bill;
import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.meal.Meal;
import com.RestaurantAppV2.meal.MealType;
import com.RestaurantAppV2.order.Order;
import com.RestaurantAppV2.order.dto.OrderDTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

public class MealDTO
{
    private String name;
    private MealType type;
    private double price;
    private Set<BillDTO> bills;
    private Set<OrderDTO> orders;

    public MealDTO()
    {
    }

    public MealDTO(double price)
    {
        this.price = price;
    }

    public Meal toMeal()
    {
//        TODO
        return null;
    }

    public String getName()
    {
        return this.name;
    }

    public MealType getType()
    {
        return this.type;
    }

    public double getPrice()
    {
        return this.price;
    }

    public Set<BillDTO> getBills()
    {
        return this.bills;
    }

    public Set<OrderDTO> getOrders()
    {
        return this.orders;
    }
}
