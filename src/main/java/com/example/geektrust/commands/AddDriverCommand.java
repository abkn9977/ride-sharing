package com.example.geektrust.commands;

import com.example.geektrust.entities.Driver;
import com.example.geektrust.exceptions.IDException;
import com.example.geektrust.services.IUserService;

import java.util.List;

public class AddDriverCommand implements ICommand{

    private final IUserService<Driver> driverService;

    public AddDriverCommand(IUserService<Driver> driverService){
        this.driverService = driverService;
    }

    @Override
    public void execute(List<String> tokens) {
        String driverId = tokens.get(1);
        double latitude = Double.parseDouble(tokens.get(2));
        double longitude = Double.parseDouble(tokens.get(3));

        try {
            driverService.add(driverId, latitude, longitude);
        } catch (IDException e) {
            System.out.println(e.getMessage());
        }
    }
}
