package com.example.geektrust.repositories;

import com.example.geektrust.entities.Driver;

import java.util.*;

public class DriverRepository implements IRepository<Driver, String> {
    private final Map<String, Driver> driverMap;

    public DriverRepository(){
        this.driverMap = new HashMap<>();
    }

    public DriverRepository(Map<String, Driver> driverMap){
        this.driverMap = driverMap;
    }

    @Override
    public Driver save(Driver entity) {
        if(driverMap.containsKey(entity.getId()))
            return null;
        driverMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Driver> findByID(String id) {
        return Optional.ofNullable(driverMap.get(id));
    }

    @Override
    public List<Driver> findAll() {
        return new ArrayList<>(driverMap.values());
    }
}
