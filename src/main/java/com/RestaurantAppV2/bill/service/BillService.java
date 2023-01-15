package com.RestaurantAppV2.bill.service;

import com.RestaurantAppV2.bill.Bill;
import com.RestaurantAppV2.bill.BillStatus;
import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.bill.repository.BillRepository;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.table.service.TableService;
import com.RestaurantAppV2.waiter.dto.WaiterDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;


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

    public void createBill(BillDTO toSave)
    {
        this.billRepository.save(toSave.toBill());
    }

    public boolean existsByTableIdAndStatus(int tableId, BillStatus status)
    {
        return this.billRepository.existsByTableIdAndStatus(tableId, status.name());
    }

    public BillDTO openBill(RestaurantTableDTO table, WaiterDTO waiter)
    {
        this.tableService.changeTableStatusToTaken(table);

        var result = createNewBillAsDto(table, waiter, getLastBillNumberByTableNameAndMonthAndYear(table.getName(),
                LocalDateTime.now().getMonth(), LocalDateTime.now().getYear()) + 1);

        createBill(result);

        return result;
    }

    BillDTO createNewBillAsDto(RestaurantTableDTO table, WaiterDTO waiter, int number)
    {
        return new BillDTO(table, waiter, number);
    }

    void closeBill(BillDTO bill)
    {
        bill.setAmount(sumMealPricesInBill(bill));

        updateBillAmountInDb(bill.getNumber(), bill.getAmount());

        updateBillStatus(bill, BillStatus.CLOSED);
    }

    void updateBillAmountInDb(String number, double amount)
    {
        this.billRepository.updateBillAmountInDb(number, amount);
    }

    void payBill(BillDTO bill, double tip)
    {
        bill.setTip(tip);

        updateBillStatus(bill, BillStatus.PAID);
    }

    void updateBillStatus(BillDTO bill, BillStatus status)
    {
        bill.setStatus(status);
        updateBillStatusInDb(bill.getNumber(), status);
    }

    void addMealToBillAsDto(MealDTO meal, BillDTO bill)
    {
        bill.getMeals().add(meal);
    }

//    void changeBillStatus(BillDTO bill)
//    {
//        switch (bill.getStatus())
//        {
//            case OPEN ->
//            {
//                bill.setStatus(BillStatus.CLOSED);
//                bill.setAmount(sumMealPricesInBill(bill));
//            }
//            case CLOSED ->
//            {
//                bill.setStatus(BillStatus.PAID);
//                bill.setAmount(bill.getAmount() + bill.getTip());
//            }
//        }
//
//        updateBillStatusInDb(bill.getNumber(), bill.getStatus());
//    }

    double sumMealPricesInBill(BillDTO bill)
    {
       return bill.getMeals().stream().mapToDouble(MealDTO::getPrice).sum();
    }

    void updateBillStatusInDb(String number, BillStatus status)
    {
        this.billRepository.updateBillStatus(number ,status.name());
    }

    public int getLastBillNumberByTableNameAndMonthAndYear(String name, Month month, int year)
    {
        if (this.billRepository.findLastBillNumberByTableNameAndMonthAndYear(name, month, year).isPresent())
            return this.billRepository.findLastBillNumberByTableNameAndMonthAndYear(name, month, year).get();
        else
            return 0;

    }

//    int getBillsNumberByDate(LocalDate date)
//    {
//        return this.billRepository.countBillByClosedAtDate(date);
//    }
//
//    double getBillsSumByDate(LocalDate date)
//    {
//        return this.billRepository.sumBillsByClosedAtDate(date);
//    }

//    public BillDTO getOpenBillByTableAsDto(RestaurantTableDTO table)
//    {
//        return this.billRepository.findOpenBillByTableId(this.tableService.getTableIdByName(table.getName())).orElseThrow().toDto();
//    }



}
