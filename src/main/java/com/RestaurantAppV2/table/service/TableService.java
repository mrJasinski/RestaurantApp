package com.RestaurantAppV2.table.service;

import com.RestaurantAppV2.table.TableStatus;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.table.repository.TableRepository;
import com.RestaurantAppV2.table.RestaurantTable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService
{
    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository)
    {
        this.tableRepository = tableRepository;
    }

    List<RestaurantTableDTO> getFreeTablesByGivenHourAndSeats(LocalDateTime time, int seats)
    {
        var result = new ArrayList<RestaurantTableDTO>();

        var tables = getFreeTablesByGivenHourAndSeatsAsDto(time, seats);

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

    List<RestaurantTableDTO> getFreeTablesByGivenHourAndSeatsAsDto(LocalDateTime time, int seats)
    {
        return this.tableRepository.getFreeTablesByGivenHourAndSeats(time, seats)
                .stream().map(RestaurantTable::toDto).collect(Collectors.toList());
    }

    public int getTableIdByName(String name)
    {
        return  this.tableRepository.findIdByName(name).orElseThrow();
    }

    public void changeTableStatusToTaken(String name)
    {
        this.tableRepository.updateTableStatusByName(name, TableStatus.TAKEN.name());
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
}
