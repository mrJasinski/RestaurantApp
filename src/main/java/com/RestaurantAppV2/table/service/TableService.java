package com.RestaurantAppV2.table.service;

import com.RestaurantAppV2.reservation.service.ReservationService;
import com.RestaurantAppV2.table.TableStatus;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.table.repository.TableRepository;
import com.RestaurantAppV2.table.RestaurantTable;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService
{
    private final TableRepository tableRepository;
    private final ReservationService reservationService;

    public TableService(TableRepository tableRepository, ReservationService reservationService)
    {
        this.tableRepository = tableRepository;
        this.reservationService = reservationService;
    }

    List<RestaurantTableDTO> getFreeTablesByGivenSeatsAsDto(LocalDateTime time, int seats)
    {
        var result = new ArrayList<RestaurantTableDTO>();

        var tables = getFreeTablesByGivenSeatsAsDto(seats);

//        sprawdzenie czy stolik będzie wolny przez min 2h
        tables.forEach(t ->
        {
//            czy dany stolik ma rezerwację na godzinę wcześniejszą niż podana + 2h
//            jeśli nie to wpada na listę wyników

            if (!t.getReservations().isEmpty())
                t.getReservations().forEach(r ->
                {
                    if ((r.getTime()).isAfter(time) && (r.getTime()).isBefore(time.plusHours(2)))
                        t.setStatus(TableStatus.RESERVED);
                });

            if ((t.getStatus()).equals(TableStatus.FREE))
                result.add(t);
        });

        return result;
    }

    List<RestaurantTableDTO> getFreeTablesByGivenSeatsAsDto(int seats)
    {
        return this.tableRepository.getRestaurantTablesBySeats(seats)
                .stream().map(RestaurantTable::toDto).collect(Collectors.toList());
    }

    public int getTableIdByName(String name)
    {
        return  this.tableRepository.findIdByName(name).orElseThrow();
    }

    public void changeTableStatusToTaken(RestaurantTableDTO table)
    {
        table.setStatus(TableStatus.TAKEN);

        this.tableRepository.updateTableStatusByName(table.getName(), TableStatus.TAKEN.name());
    }

    void changeTableStatusToFree(String name)
    {
        this.tableRepository.updateTableStatusByName(name, TableStatus.FREE.name());
    }

    void changeTableStatusToReserved(String name)
    {
        this.tableRepository.updateTableStatusByName(name, TableStatus.RESERVED.name());
    }

    RestaurantTable createTable(RestaurantTableDTO toSave)
    {
        return this.tableRepository.save(toSave.toTable());
    }


//  te ponad najpewniej pójdą do wykarczowania
//
//

    List<RestaurantTableDTO> getAvailableTablesBySeatsAsDto(int seats)
    {
        return this.tableRepository.getAvailableRestaurantTablesBySeats(seats)
                .stream().map(RestaurantTable::toDto).collect(Collectors.toList());
    }

    public List<RestaurantTableDTO> checkAvailableTablesByTimeAndSeatsAsDto(int seats, LocalDateTime time)
    {
        //        to już na poziomie kontrolera jeśli lista jest pusta to info o braku wolnych stolików?

//        var tables = getAvailableTablesBySeatsAsDto(seats);
        var tables = this.tableRepository.getAvailableRestaurantTablesBySeats(seats).stream().map(RestaurantTable::toDto).collect(Collectors.toList());

//        "wycięcie" stolików które mają rezerwację przed upływem dwóch godzin

        tables.forEach(t ->
        {
            if (t.getReservations() != null)
//                t.getReservations().forEach(r ->
//                {
//                    if (((r.getTime()).isAfter(time) && (r.getTime()).isBefore(time.plusHours(2))))
//                        tables.remove(t);
//                });
                t.getReservations().removeIf(r -> (r.getTime()).isAfter(time) && (r.getTime()).isBefore(time.plusHours(2)));

        });

        tables.removeIf(t -> this.reservationService.checkIfTableHasReservationsUnderTwoHoursFromNowByName(t.getName()));

        return tables;
//        return tables;
    }

    void cleanTable(RestaurantTableDTO table)
    {
        var time = LocalDateTime.now();

        table.setStatus(TableStatus.FREE);

        if (!table.getReservations().isEmpty())
            table.getReservations().forEach(r ->
            {
                if ((r.getTime()).isAfter(time) && (r.getTime()).isBefore(time.plusHours(2)))
                    table.setStatus(TableStatus.RESERVED);
            });
    }
}
