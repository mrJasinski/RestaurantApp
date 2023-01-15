package com.RestaurantAppV2.table;

import com.RestaurantAppV2.reservation.Reservation;
import com.RestaurantAppV2.reservation.dto.ReservationDTO;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    public RestaurantTable()
    {
    }

    public RestaurantTable(int seats)
    {
        this.seats = seats;
        this.reservations = new HashSet<>();
    }

    public RestaurantTable(String name, int seats)
    {
        this.name = name;
        this.seats = seats;
        this.reservations = new HashSet<>();
    }

    public RestaurantTableDTO toDto()
    {
        var result = new RestaurantTableDTO();

        result.setName(this.name);
        result.setSeats(this.seats);
        result.setStatus(this.status);
//        result.setReservations(this.reservations.stream().map(Reservation::toDto).collect(Collectors.toList()));

        var reservations = new ArrayList<ReservationDTO>();
        this.reservations.forEach(r -> reservations.add(new ReservationDTO(r.getTime(), r.getGuestName(), result)));

        result.setReservations(reservations);

        return result;
    }

    public void addReservation(Reservation reservation)
    {
        this.reservations.add(reservation);
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
