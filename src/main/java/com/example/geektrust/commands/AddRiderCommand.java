package com.example.geektrust.commands;

import com.example.geektrust.entities.Rider;
import com.example.geektrust.exceptions.IDException;
import com.example.geektrust.services.IUserService;

import java.util.List;

public class AddRiderCommand implements ICommand{

    private final IUserService<Rider> riderService;

    public AddRiderCommand(IUserService<Rider> riderService){
        this.riderService = riderService;
    }

    @Override
    public void execute(List<String> tokens) {

        String riderId = tokens.get(1);
        double latitude = Double.parseDouble(tokens.get(2));
        double longitude = Double.parseDouble(tokens.get(3));

        try {
            riderService.add(riderId, latitude, longitude);
        } catch (IDException e) {
            System.out.println(e.getMessage());
        }
    }
}
