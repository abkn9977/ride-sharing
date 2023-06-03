package com.geektrust.services;


import com.geektrust.entities.Driver;
import com.geektrust.exceptions.IDException;
import com.geektrust.repositories.IRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("DriverService Test Unit")
@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    IRepository<Driver, String> repository;

    @InjectMocks
    DriverService driverService;

    @DisplayName("Should return newly created driver")
    @Test
    public void add_shouldReturnDriver() throws IDException {
        //arrange
        Driver driver = new Driver("D1", 1, 4);

        when(repository.save(any())).thenReturn(driver);

        //act
        Driver actual = driverService.add("D1", 1, 4);

        //assert
        assertEquals(driver, actual);
    }

    @DisplayName("Should throw exception")
    @Test
    public void add_shouldThrowIDException() throws IDException {
        //arrange
        when(repository.save(any())).thenReturn(null);

        //act and assert
        assertThrows(IDException.class, () -> driverService.add("D1", 1, 4));
    }
}
