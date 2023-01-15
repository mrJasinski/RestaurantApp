package com.RestaurantAppV2.bill.service;

import com.RestaurantAppV2.bill.BillStatus;
import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.bill.repository.BillRepository;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.table.service.TableService;
import com.RestaurantAppV2.waiter.dto.WaiterDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BillServiceTest
{
    @Test
    void createNewBillAsDto_shouldReturnBillDTOWithGivenData()
    {
//        given
        var table = new RestaurantTableDTO("A1", 0);
        var waiter = new WaiterDTO();
        var number = 1;

//        system under test
        var toTest = new BillService(null, null);

//        when
        var result = toTest.createNewBillAsDto(table, waiter, number);

//        then
        assertEquals(table.getName(), result.getTable().getName());
        System.out.printf("Oczekiwana nazwa stolika - %s%n", table.getName());
        System.out.printf("Zwrócona nazwa stolika - %s%n", result.getTable().getName());

        assertEquals(String.format("%s/%s/%s%s", number, table.getName(), LocalDate.now().getMonth(), LocalDate.now().getYear()), result.getNumber());
        System.out.printf("Oczekiwany numer rachunku - %s/%s/%s%s%n", number, table.getName(), LocalDate.now().getMonth(), LocalDate.now().getYear());
        System.out.printf("Zwrócony  numer rachunku - %s%n", result.getNumber());

    }

    @Test
    void getLastBillNumberByTableNameAndMonthAndYear_shouldReturnLastBillNumberForGivenTableAndMonthAndYear()
    {
//        given
        var name = "A1";
        var month = LocalDate.now().getMonth();
        var year = LocalDate.now().getYear();

        var mockBillRepository = mock(BillRepository.class);
        when(mockBillRepository.findLastBillNumberByTableNameAndMonthAndYear(name, month, year))
                .thenReturn(Optional.of(3));

//        system under test
        var toTest = new BillService(mockBillRepository, null);

//        when
        var result = toTest.getLastBillNumberByTableNameAndMonthAndYear(name, month, year);

//        then
        assertEquals(3, result);
    }

    @Test
    void getLastBillNumberByTableNameAndMonthAndYear_shouldReturnZeroIfNoPreviousNumbersForGivenTableAndMonthAndYear()
    {
//        given
        var name = "A1";
        var month = LocalDate.now().getMonth();
        var year = LocalDate.now().getYear();

        var mockBillRepository = mock(BillRepository.class);
        when(mockBillRepository.findLastBillNumberByTableNameAndMonthAndYear(name, month, year))
                .thenReturn(Optional.empty());

//        system under test
        var toTest = new BillService(mockBillRepository, null);

//        when
        var result = toTest.getLastBillNumberByTableNameAndMonthAndYear(name, month, year);

//        then
        assertEquals(0, result);
    }

//    public BillDTO openBill(RestaurantTableDTO table, WaiterDTO waiter)
//    {
//        this.tableService.changeTableStatusToTaken(table);
//
//        var result = createNewBillAsDto(table, waiter, getLastBillNumberByTableNameAndMonthAndYear(table.getName(),
//                LocalDateTime.now().getMonth(), LocalDateTime.now().getYear()) + 1);
//
//        createBill(result);
//
//        return result;
//    }

    @Test
    void openBill_shouldReturnBillDTO()
    {
//        given
        var table = new RestaurantTableDTO("A1", 0);
        var waiter = new WaiterDTO();

        var month = LocalDate.now().getMonth();
        var year = LocalDate.now().getYear();

        var mockTableService = mock(TableService.class);
        var mockBillRepository = mock(BillRepository.class);
        when(mockBillRepository.findLastBillNumberByTableNameAndMonthAndYear(table.getName(), month, year))
                .thenReturn(Optional.of(3));

//        system under test
        var toTest = new BillService(null, mockTableService);

//        when
        var result = toTest.openBill(table, waiter);

//        then


    }

//    void updateBillStatus(BillDTO bill, BillStatus status)
//    {
//        bill.setStatus(status);
//        updateBillStatusInDb(bill.getNumber(), status);
//    }

//    void payBill(BillDTO bill, double tip)
//    {
//        bill.setTip(tip);
//
//        updateBillStatus(bill, BillStatus.PAID);
//    }

    @Test
    void payBill_shouldSetTipAmountToBillAndUpdateBillStatusToPaid()
    {

    }

    @Test
    void addMealToBillAsDto_shouldAddMealToBillMealsList()
    {
//        given
        var bill = new BillDTO();
        var meal = new MealDTO();

//        system under test
        var toTest = new BillService(null, null);

//        when
        toTest.addMealToBillAsDto(meal, bill);

//        then
        assertEquals(1, bill.getMeals().size());
        assertEquals(meal, bill.getMeals().get(0));

    }

//    double sumMealPricesInBill(BillDTO bill)
//    {
//       return bill.getMeals().stream().mapToDouble(MealDTO::getPrice).sum();
//    }

    @Test
    void sumMealPricesInBill_shouldReturnSummedPricesOfMealInBill()
    {
//        given
        var bill1 = new BillDTO();
        var bill2 = new BillDTO();
        var bill3 = new BillDTO();

        var meal1 = new MealDTO(12);
        var meal2 = new MealDTO(8);
        var meal3 = new MealDTO(10);
        var meal4 = new MealDTO(15);
        var meal5 = new MealDTO(19);
        var meal6 = new MealDTO(11);

        bill1.setMeals(List.of(meal1, meal2, meal3));
        bill2.setMeals(List.of(meal4, meal5, meal6));
        bill3.setMeals(List.of(meal1, meal2));

//        system under test
        var toTest = new BillService(null, null);

//        when
        var result1 = toTest.sumMealPricesInBill(bill1);
        var result2 = toTest.sumMealPricesInBill(bill2);
        var result3 = toTest.sumMealPricesInBill(bill3);

//        then
        assertEquals(30, result1);
        assertEquals(45, result2);
        assertEquals(20, result3);

    }

//    schemat do kopiowania
//        given
//        system under test
//        when
//        then
}

