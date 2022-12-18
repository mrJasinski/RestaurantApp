package com.RestaurantAppV2.waiter.service;

import com.RestaurantAppV2.waiter.Waiter;
import com.RestaurantAppV2.waiter.dto.WaiterDTO;
import com.RestaurantAppV2.waiter.repository.WaiterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
public class WaiterService
{
    private final WaiterRepository waiterRepository;

    public WaiterService(WaiterRepository waiterRepository)
    {
        this.waiterRepository = waiterRepository;
    }

    public WaiterDTO assignWaiter()
    {
//        przypisania kelnera o najniższej ilości otwartych rachunków
//        użyty set aby w przypadku tej samej ilości rachunków wybrać losowo
        return this.waiterRepository.findWaiterWithLowestBillsNumber().stream().findFirst().orElseThrow().toDto();
    }

    Waiter createWaiter(WaiterDTO toSave)
    {
        return this.waiterRepository.save(toSave.toWaiter());
    }

    WaiterDTO getWaiterWithHighestBillsSumByDate(LocalDate date)
    {
        return this.waiterRepository.findWaiterWithHighestBillsSumByDate(date).toDto();
    }

    HashMap<WaiterDTO, Integer> getWaitersWithBillsNumbersAsDtoByDate(LocalDate date)
    {
        var result = new HashMap<WaiterDTO, Integer>();

        this.waiterRepository.findWaitersWithBillsNumbersByDate(date).forEach((k, v) -> result.put(k.toDto(), v));

        return result;
    }
}
