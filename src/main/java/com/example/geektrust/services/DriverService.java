package com.example.geektrust.services;

import com.example.geektrust.entities.Driver;
import com.example.geektrust.exceptions.IDException;
import com.example.geektrust.exceptions.NotFoundException;
import com.example.geektrust.repositories.IRepository;

import java.util.List;

public class DriverService implements IUserService<Driver> {

    IRepository<Driver, String> driverStringIRepository;

    public DriverService(IRepository<Driver, String> driverStringIRepository){
        this.driverStringIRepository = driverStringIRepository;
    }

    @Override
    public Driver add(String id, double latitude, double longitude) throws IDException {
        Driver driver = driverStringIRepository.save(new Driver(id, latitude, longitude));

        if(driver == null)
            throw new IDException("Driver with id " + id + " already exists!");

        return driver;
    }

    @Override
    public Driver get(String id) throws NotFoundException {
        return driverStringIRepository.findByID(id).orElseThrow(() -> new NotFoundException("Driver with id " + id + " not found!"));
    }

    @Override
    public List<Driver> getAll() {
        return driverStringIRepository.findAll();
    }
}
