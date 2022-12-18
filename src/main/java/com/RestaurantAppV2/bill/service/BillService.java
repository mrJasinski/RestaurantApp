package com.RestaurantAppV2.bill.service;

import com.RestaurantAppV2.bill.Bill;
import com.RestaurantAppV2.bill.BillStatus;
import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.bill.repository.BillRepository;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.table.service.TableService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BillService
{
    private final BillRepository billRepository;
    private final TableService tableService;

    public BillService(BillRepository billRepository, TableService tableService)
    {
        this.billRepository = billRepository;
        this.tableService = tableService;
    }

    void addMealToBill(RestaurantTableDTO table, MealDTO meal)
    {
        this.billRepository.findOpenBillByTableId(this.tableService.getTableIdByName(table.getName()))
                .ifPresent(b -> b.addMeal(meal.toMeal()));
    }

    BillDTO getOpenBillByTableAsDto(RestaurantTableDTO table)
    {
        return this.billRepository.findOpenBillByTableId(this.tableService.getTableIdByName(table.getName())).orElseThrow().toDto();
    }

    public Bill createBill(BillDTO toSave)
    {
        return this.billRepository.save(toSave.toBill());
    }

    public boolean existsByTableIdAndStatus(int tableId, BillStatus status)
    {
        return this.billRepository.existsByTableIdAndStatus(tableId, status.name());
    }

    void changeBillStatus(BillDTO bill)
    {
        switch (bill.getStatus())
        {
            case OPEN -> bill.setStatus(BillStatus.CLOSED);
            case CLOSED -> bill.setStatus(BillStatus.PAID);
        }

        updateBillStatusInDb(bill.getNumber(), bill.getStatus());
    }

    void updateBillStatusInDb(String number, BillStatus status)
    {
        this.billRepository.updateBillStatus(number ,status.name());
    }

    int getBillsNumberByDate(LocalDate date)
    {
        return this.billRepository.countBillByClosedAtDate(date);
    }

    double getBillsSumByDate(LocalDate date)
    {
        return this.billRepository.sumBillsByClosedAtDate(date);
    }
}
