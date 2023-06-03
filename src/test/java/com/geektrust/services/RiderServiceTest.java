package com.geektrust.services;

import com.geektrust.entities.Rider;
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

@DisplayName("RiderService Test Unit")
@ExtendWith(MockitoExtension.class)
public class RiderServiceTest {

    @Mock
    IRepository<Rider, String> repository;

    @InjectMocks
    RiderService riderService;

    @DisplayName("Should return newly created Rider")
    @Test
    public void add_shouldReturnRider() throws IDException {
        //arrange
        Rider rider = new Rider("R1", 3, 5);
        when(repository.save(any())).thenReturn(rider);

        //act
        Rider actual = riderService.add("R1", 3, 5);

        //assert
        assertEquals(rider, actual);
    }
    @DisplayName("Should throw ID Exception")
    @Test
    public void add_shouldThrowIDException() throws IDException {
        //arrange
        when(repository.save(any())).thenReturn(null);

        //act and assert
        assertThrows(IDException.class, () -> riderService.add("R1", 3, 6));
    }
}