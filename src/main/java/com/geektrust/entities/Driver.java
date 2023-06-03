package com.geektrust.entities;

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
        this.status = status;
    }
}