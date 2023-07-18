package com.example.geektrust.services;

import com.example.geektrust.exceptions.IDException;
import com.example.geektrust.exceptions.NotFoundException;

import java.util.List;

public interface IUserService<T> {
    T add(String id, double latitude, double longitude) throws IDException;
    T get(String id) throws NotFoundException;
    List<T> getAll();
}
