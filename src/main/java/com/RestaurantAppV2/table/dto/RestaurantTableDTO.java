package com.RestaurantAppV2.table.dto;

import com.RestaurantAppV2.reservation.Reservation;
import com.RestaurantAppV2.reservation.dto.ReservationDTO;
import com.RestaurantAppV2.table.RestaurantTable;
import com.RestaurantAppV2.table.TableStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTableDTO
{
    private String name;
    private int seats;
    private TableStatus status;
    private List<ReservationDTO> reservations;

    public RestaurantTableDTO()
    {
        this.reservations = new ArrayList<>();
    }

    public RestaurantTableDTO(String name, int seats)
    {
        this.name = name;
        this.seats = seats;
    }

    public RestaurantTableDTO(String name, int seats, TableStatus status, List<ReservationDTO> reservations)
    {
        this.name = name;
        this.seats = seats;
        this.status = status;
        this.reservations = reservations;
    }

    public RestaurantTableDTO(String name, int seats, TableStatus status)
    {
        this.name = name;
        this.seats = seats;
        this.status = status;
    }

    public RestaurantTableDTO(RestaurantTable table)
    {
        this.name = table.getName();
        this.seats = table.getSeats();
        this.status = table.getStatus();
    }

    public void addReservation(ReservationDTO reservation)
    {
        this.reservations.add(reservation);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getSeats()
    {
        return this.seats;
    }

    public void setSeats(int seats)
    {
        this.seats = seats;
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

    public void setReservations(List<ReservationDTO> reservations)
    {
        this.reservations = reservations;
    }

    public RestaurantTable toTable()
    {
//        TODO
        return null;
    }
}
