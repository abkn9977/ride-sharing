package com.geektrust;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Integration Test Unit")
public class MainTest {

    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @DisplayName("Testing run method: should return correct output")
    @Test
    public void runTest1(){
        //arrange
        List<String> arguments = new ArrayList<>(List.of("sample_input/input1.txt"));

        String expected = "DRIVERS_MATCHED D1 D3\r\n" +
                "RIDE_STARTED RIDE-001\r\n" +
                "RIDE_STOPPED RIDE-001\r\n" +
                "BILL RIDE-001 D3 186.72";

        //act
        Main.run(arguments);
        String actual = outputStreamCaptor.toString().trim();

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Testing run method: should return correct output")
    @Test
    public void runTest2(){
        //arrange
        List<String> arguments = new ArrayList<>(List.of("sample_input/input2.txt"));

        String expected = "DRIVERS_MATCHED D2 D3 D1\r\n" +
                "DRIVERS_MATCHED D1 D2 D3\r\n" +
                "RIDE_STARTED RIDE-101\r\n" +
                "RIDE_STARTED RIDE-102\r\n" +
                "RIDE_STOPPED RIDE-101\r\n" +
                "RIDE_STOPPED RIDE-102\r\n" +
                "BILL RIDE-101 D2 234.64\r\n" +
                "BILL RIDE-102 D1 258.00";

        //act
        Main.run(arguments);
        String actual = outputStreamCaptor.toString().trim();

        //assert
        assertEquals(expected, actual);
    }

    public void tearDown(){
        System.setOut(standardOutput);
    }

}