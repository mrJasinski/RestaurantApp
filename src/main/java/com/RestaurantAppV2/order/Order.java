package com.RestaurantAppV2.order;

import com.RestaurantAppV2.bill.Bill;
import com.RestaurantAppV2.meal.Meal;
import com.RestaurantAppV2.order.dto.OrderDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public OrderDTO toDto()
    {
//        TODO
        return null;
    }
}
