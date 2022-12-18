package com.RestaurantAppV2.reservation.repository;

import com.RestaurantAppV2.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlReservationRepository extends ReservationRepository, JpaRepository<Reservation, Integer>
{

}
