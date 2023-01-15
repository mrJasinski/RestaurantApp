package com.RestaurantAppV2.bill.dto;

import com.RestaurantAppV2.bill.Bill;
import com.RestaurantAppV2.bill.BillStatus;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.waiter.dto.WaiterDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDTO
{
    private String number;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private List<MealDTO> meals;
    private RestaurantTableDTO table;
    private WaiterDTO waiter;
    private BillStatus status;
    private double tip;
    private double amount;

    public BillDTO()
    {
        this.meals = new ArrayList<>();
    }

    public BillDTO(RestaurantTableDTO table, WaiterDTO waiter, int number)
    {
        this.table = table;
        this.waiter = waiter;
        this.status = BillStatus.OPEN;
        this.createdAt = LocalDateTime.now();
        this.number = String.format("%s/%s/%s%s", number, table.getName(), this.createdAt.getMonth(), this.createdAt.getYear());
        this.amount = 0;
        this.meals = new ArrayList<>();
    }

    public void addMeal(MealDTO meal)
    {
        this.meals.add(meal);
    }

    public Bill toBill()
    {
//        TODO
        return null;
    }

    public List<MealDTO> getMeals()
    {
        return this.meals;
    }

    public void setMeals(List<MealDTO> meals)
    {
        this.meals = meals;
    }

    public RestaurantTableDTO getTable()
    {
        return this.table;
    }

    public WaiterDTO getWaiter()
    {
        return this.waiter;
    }

    public BillStatus getStatus()
    {
        return this.status;
    }

    public void setStatus(BillStatus status)
    {
        this.status = status;
    }

    public String getNumber()
    {
        return number;
    }

    public double getTip()
    {
        return tip;
    }

    public void setTip(double tip)
    {
        this.tip = tip;
    }

    public double getAmount()
    {
        return this.amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }
}
