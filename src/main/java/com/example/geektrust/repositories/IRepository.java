package com.example.geektrust.repositories;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, ID>{
    T save(T entity);
    Optional<T> findByID(ID id);
    List<T> findAll();
}