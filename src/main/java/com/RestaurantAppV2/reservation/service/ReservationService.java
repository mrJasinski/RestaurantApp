package com.RestaurantAppV2.reservation.service;

import com.RestaurantAppV2.reservation.repository.ReservationRepository;
import com.RestaurantAppV2.reservation.dto.ReservationDTO;
import com.RestaurantAppV2.reservation.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReservationService
{
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository)
    {
        this.reservationRepository = reservationRepository;
    }

    Reservation createReservation(ReservationDTO toSave)
    {
        return this.reservationRepository.save(toSave.toReservation());
    }

    void cancelReservation(ReservationDTO toDelete)
    {
        this.reservationRepository.delete(toDelete.toReservation());
    }

    Reservation updateReservation(ReservationDTO toUpdate)
    {
//        TODO
        return null;
    }

    public boolean checkIfTableHasReservationsUnderTwoHoursFromNowByName(String name)
    {
//        TODO wyniesienie time do HQL?
       return this.reservationRepository.findReservationsByTableNameAndDay(name, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    }
}
