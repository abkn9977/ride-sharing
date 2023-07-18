package com.example.geektrust.services;

import com.example.geektrust.entities.Rider;
import com.example.geektrust.exceptions.IDException;
import com.example.geektrust.exceptions.NotFoundException;
import com.example.geektrust.repositories.IRepository;

import java.util.List;

public class RiderService implements IUserService<Rider> {

    private final IRepository<Rider, String> repository;

    public RiderService(IRepository<Rider, String> repository){
        this.repository = repository;
    }

    @Override
    public Rider add(String id, double latitude, double longitude) throws IDException {
        Rider rider = repository.save(new Rider(id, latitude, longitude));

        if(rider == null)
            throw new IDException("Rider with " + id + " already exists");

        return rider;
    }

    @Override
    public Rider get(String id) throws NotFoundException {
        return repository.findByID(id).orElseThrow(() -> new NotFoundException("No rider present with id " + id));
    }

    @Override
    public List<Rider> getAll() {
        return repository.findAll();
    }
}