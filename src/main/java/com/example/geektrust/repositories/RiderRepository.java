package com.example.geektrust.repositories;

import com.example.geektrust.entities.Rider;

import java.util.*;


public class RiderRepository implements IRepository<Rider, String> {
    private final Map<String, Rider> riderMap;

    public RiderRepository(){
        this.riderMap = new HashMap<>();
    }

    public RiderRepository(Map<String, Rider> riderMap){
        this.riderMap = riderMap;
    }

    @Override
    public Rider save(Rider entity) {
        if(riderMap.containsKey(entity.getId()))
            return null;

        riderMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Rider> findByID(String id) {
        return Optional.ofNullable(riderMap.get(id));
    }

    @Override
    public List<Rider> findAll() {
        return new ArrayList<>(riderMap.values());   }
}
