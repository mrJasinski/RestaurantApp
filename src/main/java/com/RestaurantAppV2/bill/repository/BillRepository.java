package com.RestaurantAppV2.bill.repository;

import com.RestaurantAppV2.bill.Bill;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface BillRepository
{
    Bill save(Bill entity);

    Optional<Bill> findOpenBillByTableId(Integer tableId);
    Optional<Bill> findBillByNumber(String number);

    Boolean existsByTableIdAndStatus(Integer tableId, String status);

    void updateBillStatus(String number, String status);

    Integer countBillByClosedAtDate(LocalDate date);

    Double sumBillsByClosedAtDate(LocalDate date);

    Optional<Integer> findLastBillNumberByTableNameAndMonthAndYear(String name, Month month, int year);

    void updateBillAmountInDb(String number, Double amount);
}
