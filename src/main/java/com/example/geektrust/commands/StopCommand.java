package com.example.geektrust.commands;

import com.example.geektrust.exceptions.NotFoundException;
import com.example.geektrust.services.RideService;

import java.util.List;

public class StopCommand implements ICommand{
    private final RideService rideService;

    public StopCommand(RideService rideService){
        this.rideService = rideService;
    }


    @Override
    public void execute(List<String> tokens) {
        try{
            double dLatitude = Double.parseDouble(tokens.get(2));
            double dLongitude = Double.parseDouble(tokens.get(3));
            double duration = Double.parseDouble(tokens.get(4));

            String stop = rideService.stop(tokens.get(1), dLatitude, dLongitude, duration);
            System.out.println(stop);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
