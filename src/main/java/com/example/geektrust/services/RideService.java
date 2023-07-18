package com.example.geektrust.services;

import com.example.geektrust.entities.*;
import com.example.geektrust.exceptions.NotFoundException;
import com.example.geektrust.repositories.IRepository;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RideService {

    private final RiderService riderService;
    private final DriverService driverService;
    private final IRepository<Ride, String> rideRepository;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public RideService(RiderService riderService, DriverService driverService, IRepository<Ride, String> rideRepository){
        this.riderService = riderService;
        this.driverService = driverService;
        this.rideRepository = rideRepository;
    }

    /***
     * Finds maximum 5 riders withing 5 KM range from rider's coordinates
     */
    public String match(String riderId) throws NotFoundException {
        Rider rider = riderService.get(riderId);

        List<String> driverIds = availableDrivers(rider.getLatitude(), rider.getLongitude()).stream().limit(5).collect(Collectors.toList());

        if(driverIds.isEmpty())
            return "NO_DRIVERS_AVAILABLE";
        //set available drivers to rider's matches
        rider.setMatches(driverIds);

        return "DRIVERS_MATCHED " + String.join(" ", driverIds);
    }
    /**
     * Finds all the drivers within range, filter IDs and sort them in ascending order
     * according to distance
     * Note: this should be private, is public for testing
     */

    public List<String> availableDrivers(double latitude, double longitude) {
        List<Driver> drivers = driverService.getAll();

        return drivers.stream()
                .filter(d -> d.getStatus() != DriverStatus.TRANSIT && distance(latitude, longitude, d.getLatitude(), d.getLongitude()) <= 5)
                .sorted(new Comparator<Driver>() {
                    @Override
                    public int compare(Driver d1, Driver d2) {
                        double dist1 = distance(latitude, longitude, d1.getLatitude(), d1.getLongitude());
                        double dist2 = distance(latitude, longitude, d2.getLatitude(), d2.getLongitude());

                        //if distance is same, sort lexicographically
                        if(Double.compare(dist1, dist2) == 0)
                            return d1.getId().compareTo(d2.getId());

                        return Double.compare(dist1, dist2);
                    }
                })
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
    }

    /**
     * Start method starts the ride after matching is done
     */
    public String start(String rideId, int nDriver, String riderId) throws NotFoundException {

        Rider rider = riderService.get(riderId);

        if(rider.getMatches().size() < nDriver)
            return "INVALID_RIDE";

        String driverId = rider.getMatches().get(nDriver - 1);

        Driver driver = driverService.get(driverId);

        //if driver is not available
        if(driver.getStatus() != DriverStatus.WAITING)
            return "INVALID_RIDE";

        Ride ride = rideRepository.save(new Ride(rideId, driverId, riderId));

        if(ride == null)
            return "INVALID_RIDE";

        //set starting coordinates, which is equal to rider's coordinate
        ride.setLatitude(rider.getLatitude());
        ride.setLongitude(rider.getLongitude());
        //set status to PROGRESS
        ride.setStatus(RideStatus.PROGRESS);

        //Set driver status to TRANSIT
        driver.setStatus(DriverStatus.TRANSIT);

        return "RIDE_STARTED " + rideId;
    }

    /**
     * Stops the ride and calculates bill amount
     */
    public String stop(String rideId, double dLatitude, double dLongitude, double duration) throws NotFoundException {

        Ride ride = rideRepository.findByID(rideId).orElseThrow(() -> new NotFoundException("INVALID_RIDE"));

        if(ride.getStatus() != RideStatus.PROGRESS)
            return "INVALID_RIDE";

        //update ride's state
        ride.setStatus(RideStatus.COMPLETED);
        ride.setdLatitude(dLatitude);
        ride.setdLongitude(dLongitude);
        ride.setDuration(duration);
        ride.setDistance(distance(ride.getLatitude(), ride.getLongitude(), dLatitude, dLongitude));

        //calculate bill and update
        ride.setBillAmount(calculateBill(ride));

        //update driver's state
        Driver driver = driverService.get(ride.getDriverId());
        driver.setStatus(DriverStatus.WAITING);

        return "RIDE_STOPPED " + rideId;
    }

    /**
     * Calculates bill for the ride
     * Note: this should be private, is public for testing
     */
    public String calculateBill(Ride ride) {
        //base fare
        double totalBill = 50;

        //additional 6.5 for every km
        totalBill += (6.5 * ride.getDistance());

        //additional 2 for every min spent in ride
        totalBill += (2 * ride.getDuration());

        //20% extra on totalBill

        totalBill += (20 * totalBill / 100);

        return df.format(totalBill);
    }

    /**
     * Return bill for the ride
     */
    public String getBill(String rideId) throws NotFoundException {
        Ride ride = rideRepository.findByID(rideId).orElseThrow(() -> new NotFoundException("INVALID_RIDE"));

        if(ride.getStatus() != RideStatus.COMPLETED)
            throw new RuntimeException("RIDE_NOT_COMPLETED");

        return "BILL " + rideId + " " + ride.getDriverId() + " " + ride.getBillAmount();
    }

    /**
     * Calculates the Euclidean distance between two points
     * Note: this should be private, is public for testing
     */
    public double distance(double x1, double y1, double x2, double y2){
        double x = Math.abs(x2 - x1);
        double y = Math.abs(y2 - y1);

    //  multiply dist with 100 to move decimal 2 points to right
    //  int dist = (int)(Math.sqrt(x * x + y * y) * 100);
    //  return (double)dist / 100;

        String dist = df.format(Math.sqrt(x * x + y * y));

        return Double.parseDouble(dist);
    }
}