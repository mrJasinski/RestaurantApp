package com.RestaurantAppV2.bill.dto;

import com.RestaurantAppV2.bill.Bill;
import com.RestaurantAppV2.bill.BillStatus;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.waiter.dto.WaiterDTO;

import java.util.List;

public class BillDTO
{
    private String number;
    private List<MealDTO> meals;
    private RestaurantTableDTO table;
    private WaiterDTO waiter;
    private BillStatus status;
    private double tip;

    public BillDTO(RestaurantTableDTO table, WaiterDTO waiter)
    {
        this.table = table;
        this.waiter = waiter;
        this.status = BillStatus.OPEN;
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
}
