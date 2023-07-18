package com.example.geektrust.commands;

import com.example.geektrust.exceptions.NotFoundException;
import com.example.geektrust.services.RideService;

import java.util.List;

public class StartCommand implements ICommand{

    private final RideService rideService;

    public StartCommand(RideService rideService){
        this.rideService = rideService;
    }


    @Override
    public void execute(List<String> tokens) {
        try{
            String start = rideService.start(tokens.get(1), Integer.parseInt(tokens.get(2)), tokens.get(3));
            System.out.println(start);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
