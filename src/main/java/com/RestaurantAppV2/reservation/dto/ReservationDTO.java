package com.RestaurantAppV2.reservation.dto;

import com.RestaurantAppV2.reservation.Reservation;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;

import java.time.LocalDateTime;

public class ReservationDTO
{
    private LocalDateTime time;
    private String name;
    private RestaurantTableDTO table;

    public ReservationDTO()
    {
    }

    public ReservationDTO(LocalDateTime time, String name, RestaurantTableDTO table)
    {
        this.time = time;
        this.name = name;
        this.table = table;
    }

    public Reservation toReservation()
    {
//        TODO
        return null;
    }

    public LocalDateTime getTime()
    {
        return this.time;
    }

    public String getName()
    {
        return this.name;
    }

    public RestaurantTableDTO getTable()
    {
        return this.table;
    }
}
