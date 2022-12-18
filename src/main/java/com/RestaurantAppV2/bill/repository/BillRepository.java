package com.RestaurantAppV2.bill.repository;

import com.RestaurantAppV2.bill.Bill;

import java.time.LocalDate;
import java.util.Optional;

public interface BillRepository
{
    Bill save(Bill entity);

    Optional<Bill> findOpenBillByTableId(Integer tableId);

    Boolean existsByTableIdAndStatus(Integer tableId, String status);

    void updateBillStatus(String number, String status);

    Integer countBillByClosedAtDate(LocalDate date);

    Double sumBillsByClosedAtDate(LocalDate date);
}
