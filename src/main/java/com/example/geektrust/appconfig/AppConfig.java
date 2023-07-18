package com.example.geektrust.appconfig;

import com.example.geektrust.commands.*;
import com.example.geektrust.entities.Driver;
import com.example.geektrust.entities.Ride;
import com.example.geektrust.entities.Rider;
import com.example.geektrust.repositories.DriverRepository;
import com.example.geektrust.repositories.IRepository;
import com.example.geektrust.repositories.RideRepository;
import com.example.geektrust.repositories.RiderRepository;
import com.example.geektrust.services.DriverService;
import com.example.geektrust.services.RideService;
import com.example.geektrust.services.RiderService;

public class AppConfig {
    //Initialize Repositories
    private final IRepository<Driver, String> driverRepository = new DriverRepository();
    private final IRepository<Rider, String> riderRepository = new RiderRepository();
    private final IRepository<Ride, String> rideRepository = new RideRepository();

    //Initialize Services
    private final DriverService driverService = new DriverService(driverRepository);
    private final RiderService riderService = new RiderService(riderRepository);
    private final RideService rideService = new RideService(riderService, driverService, rideRepository);

    //Initialize Commands
    private final ICommand addDriverCommand = new AddDriverCommand(driverService);
    private final ICommand addRiderCommand = new AddRiderCommand(riderService);
    private final ICommand billCommand = new BillCommand(rideService);
    private final ICommand matchCommand = new MatchCommand(rideService);
    private final ICommand startCommand = new StartCommand(rideService);
    private final ICommand stopCommand = new StopCommand(rideService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("ADD_DRIVER", addDriverCommand);
        commandInvoker.register("ADD_RIDER", addRiderCommand);
        commandInvoker.register("MATCH", matchCommand);
        commandInvoker.register("START_RIDE", startCommand);
        commandInvoker.register("STOP_RIDE", stopCommand);
        commandInvoker.register("BILL", billCommand);

        return this.commandInvoker;
    }
}
