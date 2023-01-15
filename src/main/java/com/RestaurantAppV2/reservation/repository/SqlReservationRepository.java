package com.RestaurantAppV2.reservation.repository;

import com.RestaurantAppV2.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SqlReservationRepository extends ReservationRepository, JpaRepository<Reservation, Integer>
{
    @Override
    @Query(value = "FROM Reservation  r WHERE r.table.name = :name AND (r.time between :time AND :timePlusTwoHours)")
    Boolean findReservationsByTableNameAndDay(String name, LocalDateTime time, LocalDateTime timePlusTwoHours);
}
