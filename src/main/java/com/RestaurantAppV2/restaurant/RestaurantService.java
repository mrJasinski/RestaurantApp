package com.RestaurantAppV2.restaurant;

import com.RestaurantAppV2.bill.BillStatus;
import com.RestaurantAppV2.bill.dto.BillDTO;
import com.RestaurantAppV2.bill.service.BillService;
import com.RestaurantAppV2.table.dto.RestaurantTableDTO;
import com.RestaurantAppV2.table.service.TableService;
import com.RestaurantAppV2.waiter.service.WaiterService;

public class RestaurantService
{
    private final BillService billService;
    private final TableService tableService;
    private final WaiterService waiterService;

    public RestaurantService(BillService billService, TableService tableService, WaiterService waiterService)
    {
        this.billService = billService;
        this.tableService = tableService;
        this.waiterService = waiterService;
    }

    void seatGuestsAtTable(RestaurantTableDTO table)
    {
//        ponieważ mam założenie że jest tylko jeden otwarty rachunek na danym stoliku to należy wpierw to sprawdzić

        if (!this.billService.existsByTableIdAndStatus(this.tableService.getTableIdByName(table.getName()), BillStatus.OPEN))
        {
            this.tableService.changeTableStatusToTaken(table.getName());

            this.billService.createBill(new BillDTO(table , this.waiterService.assignWaiter()));
        }
        else
        {
//            TODO tymczasowo
            System.out.println("Proszę zamknąć bieżący rachunek przed otwarciem nowego!");
        }

    }
}
