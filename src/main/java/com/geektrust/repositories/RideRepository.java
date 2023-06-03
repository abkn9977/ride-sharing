package com.geektrust.repositories;

import com.geektrust.entities.Ride;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RideRepository implements IRepository<Ride, String> {
    private final Map<String, Ride> rideMap;

    public RideRepository(){
        this.rideMap = new HashMap<>();
    }

    public RideRepository(Map<String, Ride> rideMap){
        this.rideMap = rideMap;
    }

    @Override
    public Ride save(Ride entity) {
        if(rideMap.containsKey(entity.getId()))
            return null;
        rideMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Ride> findByID(String id) {
        return Optional.ofNullable(rideMap.get(id));
    }

    @Override
    public List<Ride> findAll() {
        return rideMap.values().stream().toList();
    }
}