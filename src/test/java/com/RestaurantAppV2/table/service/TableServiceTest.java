package com.RestaurantAppV2.table.service;

import com.RestaurantAppV2.reservation.Reservation;
import com.RestaurantAppV2.reservation.dto.ReservationDTO;
import com.RestaurantAppV2.reservation.service.ReservationService;
import com.RestaurantAppV2.table.RestaurantTable;
import com.RestaurantAppV2.table.TableStatus;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.table.repository.TableRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TableServiceTest
{
    @Test
    void cleanTable_shouldChangeStatusTableToFreeIfNoReservationsBeforeTwoHours()
    {
//        given
        var table = new RestaurantTableDTO();

//        system under test
        var toTest = new TableService(null, null);

//        when
        toTest.cleanTable(table);

//        then
        assertEquals(table.getStatus(), TableStatus.FREE);
    }

    @Test
    void cleanTable_shouldChangeStatusTableToReservedIfHasReservationsBeforeTwoHours()
    {
//        given
        var table = new RestaurantTableDTO();
        table.addReservation(new ReservationDTO(LocalDateTime.now().plusHours(1), "Jan Jankowski", table));

//        system under test
        var toTest = new TableService(null, null);

//        when
        toTest.cleanTable(table);

//        then
        assertEquals(table.getStatus(), TableStatus.RESERVED);
    }

    @Test
    void getAvailableTablesBySeatsAsDto_shouldConvertTablesListToDto()
    {
//        given
        var table1 = new RestaurantTable(2);
        var table2 = new RestaurantTable(4);
        var table3 = new RestaurantTable(1);

        var mockTableRepo = mock(TableRepository.class);
        when(mockTableRepo.getAvailableRestaurantTablesBySeats(2)).thenReturn(List.of(table1, table2));
        when(mockTableRepo.getAvailableRestaurantTablesBySeats(1)).thenReturn(List.of(table1, table2, table3));

//        system under test
        var toTest = new TableService(mockTableRepo, null);
//        when
        var result1 = toTest.getAvailableTablesBySeatsAsDto(2);
        var result2 = toTest.getAvailableTablesBySeatsAsDto(1);

//        then
        assertEquals(2, result1.size());
        assertFalse(result1.contains(table3.toDto()));
        assertEquals(3, result2.size());
    }

//        public List<RestaurantTableDTO> checkAvailableTablesByTimeAndSeats(int seats, LocalDateTime time)
//    {
//        //        to już na poziomie kontrolera jeśli lista jest pusta to info o braku wolnych stolików?
//
//        var tables = this.tableRepository.getAvailableRestaurantTablesBySeats(seats);
//
////        wycięcie stolików które mają rezerwację przed upływem dwóch godzin
//
//        tables.forEach(t ->
//        {
//            if (!t.getReservations().isEmpty())
//                t.getReservations().forEach(r ->
//                {
//                    if ((r.getTime()).isAfter(time) && (r.getTime()).isBefore(time.plusHours(2)))
//                        tables.remove(t);
//                });
//        });
//
//        return tables.stream().map(RestaurantTable::toDto).collect(Collectors.toList());
//    }

    @Test
    void checkAvailableTablesByTimeAndSeatsAsDto_shouldReturnTablesAsAvailableBecauseNoReservationsAsDto()
    {
//        given
        var table1 = new RestaurantTable(2);
        var table2 = new RestaurantTable(4);
        var table3 = new RestaurantTable(2);
        var table4 = new RestaurantTable(1);

        var mockTableRepo = mock(TableRepository.class);
        given(mockTableRepo.getAvailableRestaurantTablesBySeats(2)).willReturn(List.of(table1, table2, table3));
        given(mockTableRepo.getAvailableRestaurantTablesBySeats(1)).willReturn(List.of(table1, table2, table3, table4));
        given(mockTableRepo.getAvailableRestaurantTablesBySeats(3)).willReturn(List.of(table2));

//        system under test
        var toTest = new TableService(mockTableRepo, null);

//        when
        var result1 = toTest.checkAvailableTablesByTimeAndSeatsAsDto(1, LocalDateTime.now());
        var result2 = toTest.checkAvailableTablesByTimeAndSeatsAsDto(2, LocalDateTime.now());
        var result3 = toTest.checkAvailableTablesByTimeAndSeatsAsDto(3, LocalDateTime.now());

//        then
        assertEquals(4, result1.size());
        assertEquals(3, result2.size());
        assertEquals(1, result3.size());
    }

    @Test
    void checkAvailableTablesByTimeAndSeatsAsDto_shouldReturnTablesAsAvailableWhenNoReservationsBeforeTwoHoursFromNowAsDto()
    {
//        given
        var table1 = new RestaurantTable("A2", 2);
        var table2 = new RestaurantTable("A3", 4);
        var table3 = new RestaurantTable( "C2",2);
        var table4 = new RestaurantTable("B1", 1);
        var table5 = new RestaurantTable("C3", 4);

        var reservation1 = new Reservation(LocalDateTime.now().plusHours(1), table2);
        var reservation2 = new Reservation(LocalDateTime.now().plusMinutes(20), table3);

        table2.addReservation(reservation1);
        table3.addReservation(reservation2);

        var mockTableRepo = mock(TableRepository.class);
        given(mockTableRepo.getAvailableRestaurantTablesBySeats(1)).willReturn(List.of(table1, table2, table3, table4, table5));
        given(mockTableRepo.getAvailableRestaurantTablesBySeats(2)).willReturn(List.of(table1, table2, table3, table5));
        given(mockTableRepo.getAvailableRestaurantTablesBySeats(3)).willReturn(List.of(table2, table5));

        var mockReservationService = mock(ReservationService.class);
        given(mockReservationService.checkIfTableHasReservationsUnderTwoHoursFromNowByName(table1.getName())).willReturn(false);
        given(mockReservationService.checkIfTableHasReservationsUnderTwoHoursFromNowByName(table2.getName())).willReturn(true);
        given(mockReservationService.checkIfTableHasReservationsUnderTwoHoursFromNowByName(table3.getName())).willReturn(true);
        given(mockReservationService.checkIfTableHasReservationsUnderTwoHoursFromNowByName(table4.getName())).willReturn(false);
        given(mockReservationService.checkIfTableHasReservationsUnderTwoHoursFromNowByName(table5.getName())).willReturn(false);

//        system under test
        var toTest = new TableService(mockTableRepo, mockReservationService);

//        when
        var result1 = toTest.checkAvailableTablesByTimeAndSeatsAsDto(1, LocalDateTime.now());
        var result2 = toTest.checkAvailableTablesByTimeAndSeatsAsDto(2, LocalDateTime.now());
        var result3 = toTest.checkAvailableTablesByTimeAndSeatsAsDto(3, LocalDateTime.now());

//        then
        assertEquals(3, result1.size());
        assertEquals(2, result2.size());
        assertEquals(1, result3.size());
    }

//    schemat do kopiowania
//        given
//        system under test
//        when
//        then
}