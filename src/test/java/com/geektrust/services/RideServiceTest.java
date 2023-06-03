package com.geektrust.services;

import com.geektrust.entities.Driver;
import com.geektrust.entities.Ride;
import com.geektrust.entities.RideStatus;
import com.geektrust.entities.Rider;
import com.geektrust.exceptions.NotFoundException;
import com.geektrust.repositories.IRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("RideService Test Unit")
@ExtendWith(MockitoExtension.class)
public class RideServiceTest {

    @Mock
    RiderService riderService;
    @Mock
    DriverService driverService;
    @Mock
    IRepository<Ride, String> rideRepo;

    @InjectMocks
    RideService rideService;


    @DisplayName("Should return only 5 drivers")
    @Test
    public void match_ShouldReturn5Drivers() throws NotFoundException {
        //arrange
        Rider rider = new Rider("R1", 10, 12);

        Driver d1 = new Driver("D1", 12, 12);
        Driver d2 = new Driver("D2", 23, 34);
        Driver d3 = new Driver("D4", 10, 16);
        Driver d4 = new Driver("D4", 10, 16);
        Driver d5 = new Driver("D4", 10, 16);
        Driver d6 = new Driver("D4", 10, 16);
        Driver d7 = new Driver("D4", 10, 16);
        Driver d8 = new Driver("D4", 10, 16);


        List<Driver> drivers = List.of(d1, d2, d3, d4, d5, d6, d7, d8);

        when(riderService.get(anyString())).thenReturn(rider);
        when(driverService.getAll()).thenReturn(drivers);

        //act
        rideService.match("R1");

        //assert
        assertEquals(5, rider.getMatches().size());

    }

    @DisplayName("Should return available drivers")
    @Test
    public void availableDrivers_shouldReturnDrivers(){
        //arrange
        Driver d1 = new Driver("D1", 10, 16);
        Driver d2 = new Driver("D2", 23, 34);
        Driver d3 = new Driver("D4", 12, 12);

        List<Driver> drivers = List.of(d1, d2, d3);
        List<String> expected = List.of("D4", "D1");

        when(driverService.getAll()).thenReturn(drivers);

        //act
        List<String> actual = rideService.availableDrivers(10, 12);

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Calculate distance for positive points")
    @Test
    public void distanceTest_withPositiveValues(){
        //arrange
        double expected = 3.61;

        //act
        double actual = rideService.distance(1, 1, 3, 4);

        //assert
        assertEquals(expected, actual);
    }


    @DisplayName("Calculate distance for negative points for X")
    @Test
    public void distanceTest_withXNegativeValues(){
        //arrange
        double expected = 8.06;

        //act
        double actual = rideService.distance(-1, -3, 3, 4);

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Calculate distance for negative points for Y")
    @Test
    public void distanceTest_withYNegativeValues(){
        //arrange
        double expected = 4.12;

        //act
        double actual = rideService.distance(1, 3, -3, 4);

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Calculate distance for negative points for both")
    @Test
    public void distanceTest_withBothNegativeValues(){
        //arrange
        double expected = 144.4;

        //act
        double actual = rideService.distance(-14, 24, 60, -100);

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Start ride should return invalid ride")
    @Test
    public void startTest_ShouldReturn_InvalidRide() throws NotFoundException {
        //arrange
        Rider rider = new Rider("R1", 2, 3);
        rider.setMatches(List.of("D1", "D2"));

        when(riderService.get(anyString())).thenReturn(rider);

        String expected = "Invalid Ride";

        //act
        String actual = rideService.start("R001", 3, "R1");

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Start ride should return invalid ride")
    @Test
    public void startTestNull_ShouldReturn_InvalidRide() throws NotFoundException {
        //arrange
        Rider rider = new Rider("R1", 2, 3);
        rider.setMatches(List.of("D1", "D2", "D3"));
        when(riderService.get(anyString())).thenReturn(rider);
        when(driverService.get(anyString())).thenReturn(new Driver("D3", 5, 5));
        when(rideRepo.save(any())).thenReturn(null);

        String expected = "Invalid Ride";

        //act
        String actual = rideService.start("R001", 3, "R1");

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Start ride should return Ride ID")
    @Test
    public void startTest_ShouldReturn_RideID() throws NotFoundException {
        //arrange

        Ride ride1 = new Ride("R001", "D3", "R1");
        when(rideRepo.save(any())).thenReturn(ride1);

        Rider rider1 = new Rider("R1", 2, 3);
        rider1.setMatches(List.of("D1", "D2", "D3"));
        when(riderService.get(anyString())).thenReturn(rider1);

        Driver driver1 = new Driver("D3", 3, 2);
        when(driverService.get(anyString())).thenReturn(driver1);

        String expected = "RIDE_STARTED R001";

        //act
        String actual = rideService.start("R001", 3, "R1");

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Stop ride should return Ride ID")
    @Test
    public void stopTest_ShouldReturn_RideID() throws NotFoundException {
        //arrange
        Ride ride1 = new Ride("R001", "D1", "R1");
        ride1.setStatus(RideStatus.PROGRESS);
        ride1.setLatitude(3);
        ride1.setLongitude(5);

        when(rideRepo.findByID(anyString())).thenReturn(Optional.of(ride1));

        Driver driver1 = new Driver("D1", 2, 2);
        when(driverService.get(anyString())).thenReturn(driver1);

        String expected = "RIDE_STOPPED R001";

        //act
        String actual = rideService.stop("R001", 10, 2, 48);

        //assert
        assertEquals(expected, actual);
    }
}
