package com.example.geektrust.entities;

import java.util.Objects;

public class Driver extends BaseEntity{
    private DriverStatus status;

    public Driver(String id, double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = DriverStatus.WAITING;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        Objects.requireNonNull(status);
        this.status = status;
    }
}