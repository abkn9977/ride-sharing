package com.example.geektrust.commands;

import com.example.geektrust.exceptions.NotFoundException;
import com.example.geektrust.services.RideService;

import java.util.List;

public class BillCommand implements ICommand{

    private final RideService rideService;

    public BillCommand(RideService rideService){
        this.rideService = rideService;
    }


    @Override
    public void execute(List<String> tokens) {
        try{
            String bill = rideService.getBill(tokens.get(1));
            System.out.println(bill);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}