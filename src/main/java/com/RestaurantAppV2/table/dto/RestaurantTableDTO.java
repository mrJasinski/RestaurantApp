package com.RestaurantAppV2.table.dto;

import com.RestaurantAppV2.reservation.dto.ReservationDTO;
import com.RestaurantAppV2.table.RestaurantTable;
import com.RestaurantAppV2.table.TableStatus;

import java.util.List;

public class RestaurantTableDTO
{
    private String name;
    private int seats;
    private TableStatus status;
    private List<ReservationDTO> reservations;

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

    public List<ReservationDTO> getReservations()
    {
        return this.reservations;
    }

    public RestaurantTable toTable()
    {
//        TODO
        return null;
    }
}
