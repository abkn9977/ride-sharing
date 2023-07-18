package com.example.geektrust.entities;

import java.util.Objects;

public class Ride extends BaseEntity{
    private String driverId;
    private String riderId;
    private double dLatitude;
    private double dLongitude;

    private RideStatus status;
    private double distance;
    private double duration;
    private String billAmount;

    public Ride(String id, String driverId, String riderId) {
        this();
        this.id = id;
        this.driverId = driverId;
        this.riderId = riderId;
    }

    public Ride() {
        this.dLatitude = 0;
        this.dLongitude = 0;
        this.status = RideStatus.STILL;
        this.distance = 0;
        this.duration = 0;
        this.billAmount = "0.00";
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public double getdLatitude() {
        return dLatitude;
    }

    public void setdLatitude(double dLatitude) {
        this.dLatitude = dLatitude;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        Objects.requireNonNull(status);
        this.status = status;
    }

    public double getdLongitude() {
        return dLongitude;
    }

    public void setdLongitude(double dLongitude) {
        this.dLongitude = dLongitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        Objects.requireNonNull(billAmount);
        this.billAmount = billAmount;
    }
}