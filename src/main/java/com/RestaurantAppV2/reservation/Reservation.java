package com.RestaurantAppV2.reservation;

import com.RestaurantAppV2.table.RestaurantTable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation
{
    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime time;
    private String guestName;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    public int getId()
    {
        return this.id;
    }

    public LocalDateTime getTime()
    {
        return this.time;
    }

    public String getGuestName()
    {
        return this.guestName;
    }

    public RestaurantTable getTable()
    {
        return this.table;
    }
}
