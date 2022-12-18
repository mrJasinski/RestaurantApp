package com.RestaurantAppV2.waiter;

import com.RestaurantAppV2.waiter.dto.WaiterDTO;

import javax.persistence.*;

@Entity
@Table(name = "waiters")
public class Waiter
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    public WaiterDTO toDto()
    {
//        TODO
        return null;
    }
}
