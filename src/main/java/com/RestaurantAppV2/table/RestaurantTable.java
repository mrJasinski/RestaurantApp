package com.RestaurantAppV2.table;

import com.RestaurantAppV2.reservation.Reservation;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tables")
public class RestaurantTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int seats;
    private TableStatus status;
    @OneToMany(mappedBy = "table")
    private Set<Reservation> reservations;

    public RestaurantTableDTO toDto()
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

    public int getSeats()
    {
        return this.seats;
    }

    public TableStatus getStatus()
    {
        return this.status;
    }

    public void setStatus(TableStatus status)
    {
        this.status = status;
    }

    public Set<Reservation> getReservations()
    {
        return this.reservations;
    }
}
