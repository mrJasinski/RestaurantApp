package com.RestaurantAppV2.bill;

import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.meal.Meal;
import com.RestaurantAppV2.order.Order;
import com.RestaurantAppV2.table.RestaurantTable;
import com.RestaurantAppV2.waiter.Waiter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bills")
public class Bill
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private String number;  //TODO autogenerowanie - np data łamana na kolejny numer z danego dnia
    @ManyToMany
    @JoinTable(name = "meals_in_bills",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Set<Meal> meals;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;
    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private Waiter waiter;
    private double tip;
    @OneToMany(mappedBy = "bill")
    private Set<Order> orders;

    public BillDTO toDto()
    {
//        TODO
        return null;
    }

    //    TODO czy to powinno się odbywać tutaj czy w dto?
    public void addMeal(Meal meal)
    {
        this.meals.add(meal);
        meal.getBills().add(this);
    }

    public int getId()
    {
        return this.id;
    }

    public LocalDateTime getCreatedAt()
    {
        return this.createdAt;
    }

    public LocalDateTime getClosedAt()
    {
        return this.closedAt;
    }

    public String getNumber()
    {
        return this.number;
    }

    public Set<Meal> getMeals()
    {
        return this.meals;
    }

    public BillStatus getStatus()
    {
        return this.status;
    }

    public RestaurantTable getTable()
    {
        return this.table;
    }

    public Waiter getWaiter()
    {
        return this.waiter;
    }

    public double getTip()
    {
        return this.tip;
    }

    public Set<Order> getOrders()
    {
        return this.orders;
    }
}
