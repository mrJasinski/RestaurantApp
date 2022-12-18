package com.RestaurantAppV2.reservation.service;

import com.RestaurantAppV2.reservation.repository.ReservationRepository;
import com.RestaurantAppV2.reservation.dto.ReservationDTO;
import com.RestaurantAppV2.reservation.Reservation;
import org.springframework.stereotype.Service;

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
}
