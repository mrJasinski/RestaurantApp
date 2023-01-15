package com.RestaurantAppV2.meal;

import com.RestaurantAppV2.bill.Bill;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.order.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "meals")
public class Meal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private MealType type;
    private double price;
//    patrz bill
//    @ManyToMany(mappedBy = "meals")
//    private Set<Bill> bills;
    @OneToMany(mappedBy = "meal")
    private Set<Order> orders;

    public MealDTO toDto()
    {
//        TODO
        return null;
    }

    public int getId()
    {
        return this.id;
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

//    public Set<Bill> getBills()
//    {
//        return this.bills;
//    }
}
