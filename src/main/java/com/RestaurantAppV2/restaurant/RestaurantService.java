package com.RestaurantAppV2.restaurant;

import com.RestaurantAppV2.bill.BillStatus;
import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.bill.service.BillService;
import com.RestaurantAppV2.meal.dto.MealDTO;
import com.RestaurantAppV2.order.OrderStatus;
import com.RestaurantAppV2.order.dto.OrderDTO;
import com.RestaurantAppV2.order.service.OrderService;
import com.RestaurantAppV2.table.TableStatus;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.table.service.TableService;
import com.RestaurantAppV2.waiter.dto.WaiterDTO;
import com.RestaurantAppV2.waiter.service.WaiterService;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantService
{
    private final BillService billService;
    private final OrderService orderService;
    private final TableService tableService;
    private final WaiterService waiterService;

    public RestaurantService(BillService billService, OrderService orderService, TableService tableService, WaiterService waiterService)
    {
        this.billService = billService;
        this.orderService = orderService;
        this.tableService = tableService;
        this.waiterService = waiterService;
    }

    String seatGuestsAtTable(RestaurantTableDTO table)
    {
//        ponieważ mam założenie że jest tylko jeden otwarty rachunek na danym stoliku to należy wpierw to sprawdzić

        if (!this.billService.existsByTableIdAndStatus(this.tableService.getTableIdByName(table.getName()), BillStatus.OPEN))
        {
            this.tableService.changeTableStatusToTaken(table);

            this.billService.openBill(table , this.waiterService.assignWaiter());

            return "Rachunek otwarty!";
        }
        else
             return "Proszę zamknąć bieżący rachunek przed otwarciem nowego!";

    }



//    TODO jak ugryźć te powiadomienia?
    void requestNotifiesAboutAvailableTablesByWaiterAndSeats(WaiterDTO waiter, int seats)
    {

    }

    String notifyWaiterAboutNewAvailableTable(RestaurantTableDTO table)
    {
//        Wywoływane przez zmianę statusu stolika(na wolny)?
        return String.format("Stolik %s (%s os) jest teraz dostępny!", table.getName(), table.getSeats());
    }











}
