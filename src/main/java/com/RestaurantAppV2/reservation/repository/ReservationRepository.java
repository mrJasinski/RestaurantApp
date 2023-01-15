package com.RestaurantAppV2.reservation.repository;

import com.RestaurantAppV2.reservation.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository
{
    Reservation save(Reservation entity);
    void delete(Reservation entity);

    Boolean findReservationsByTableNameAndDay(String name, LocalDateTime time, LocalDateTime timePlusTwoHours);
}
