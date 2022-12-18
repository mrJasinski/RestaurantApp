package com.RestaurantAppV2.reservation.repository;

import com.RestaurantAppV2.reservation.Reservation;

public interface ReservationRepository
{
    Reservation save(Reservation entity);
    void delete(Reservation entity);
}
