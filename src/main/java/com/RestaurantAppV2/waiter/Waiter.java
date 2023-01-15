package com.RestaurantAppV2.waiter;

import com.RestaurantAppV2.bill.Bill;
import com.RestaurantAppV2.waiter.dto.WaiterDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "waiters")
public class Waiter
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "waiter")
    private Set<Bill> bills;


    public WaiterDTO toDto()
    {
//        TODO
        return null;
    }
}
