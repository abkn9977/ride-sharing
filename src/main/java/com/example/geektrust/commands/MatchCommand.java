package com.example.geektrust.commands;

import com.example.geektrust.exceptions.NotFoundException;
import com.example.geektrust.services.RideService;

import java.util.List;

public class MatchCommand implements ICommand {

    private final RideService rideService;

    public MatchCommand(RideService rideService){
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            String match = rideService.match(tokens.get(1));
            System.out.println(match);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
