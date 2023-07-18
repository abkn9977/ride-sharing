package com.example.geektrust;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Integration Test Unit")
public class MainTest {

    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @DisplayName("Testing run method: should return correct output")
    @Test
    public void runTest1(){
        //arrange
        List<String> arguments = Collections.singletonList("sample_input/input1.txt");

        String expected = "DRIVERS_MATCHED D1 D3" + ls +
                "RIDE_STARTED RIDE-001" + ls +
                "RIDE_STOPPED RIDE-001" + ls +
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
        List<String> arguments = Collections.singletonList("sample_input/input2.txt");

        String expected = "DRIVERS_MATCHED D2 D3 D1" + ls +
                "DRIVERS_MATCHED D1 D2 D3" + ls +
                "RIDE_STARTED RIDE-101" + ls +
                "RIDE_STARTED RIDE-102" + ls +
                "RIDE_STOPPED RIDE-101" + ls +
                "RIDE_STOPPED RIDE-102" + ls +
                "BILL RIDE-101 D2 234.64" + ls +
                "BILL RIDE-102 D1 258.00";

        //act
        Main.run(arguments);
        String actual = outputStreamCaptor.toString().trim();

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Testing run method: should return correct output")
    @Test
    public void runTest3(){
        //arrange
        List<String> arguments = Collections.singletonList("sample_input/input3.txt");

        String expected = "NO_DRIVERS_AVAILABLE" + ls +
                "DRIVERS_MATCHED D13 D4 D2" + ls +
                "RIDE_STARTED RIDE-001" + ls +
                "RIDE_STOPPED RIDE-001" + ls +
                "BILL RIDE-001 D13 268.35";

        //act
        Main.run(arguments);
        String actual = outputStreamCaptor.toString().trim();

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Testing run method: should return correct output")
    @Test
    public void runTest4(){
        //arrange
        List<String> arguments = Collections.singletonList("sample_input/input4.txt");

        String expected = "NO_DRIVERS_AVAILABLE" + ls +
                "DRIVERS_MATCHED D1" + ls +
                "DRIVERS_MATCHED D2 D1" + ls +
                "DRIVERS_MATCHED D14 D15 D16 D1" + ls +
                "DRIVERS_MATCHED D15 D2 D1" + ls +
                "RIDE_STARTED RIDE-001" + ls +
                "DRIVERS_MATCHED D14 D16 D17 D1" + ls +
                "RIDE_STOPPED RIDE-001" + ls +
                "BILL RIDE-001 D15 327.20" + ls +
                "RIDE_STARTED RIDE-002" + ls +
                "RIDE_STOPPED RIDE-002" + ls +
                "INVALID_RIDE" + ls +
                "BILL RIDE-002 D17 440.26" + ls +
                "INVALID_RIDE" + ls +
                "BILL RIDE-002 D17 440.26";

        //act
        Main.run(arguments);
        String actual = outputStreamCaptor.toString().trim();

        //assert
        assertEquals(expected, actual);
    }

    @DisplayName("Testing run method: should return correct output")
    @Test
    public void runTest5(){
        //arrange
        List<String> arguments = Collections.singletonList("sample_input/input5.txt");

        String expected = "DRIVERS_MATCHED D1" + ls +
                "DRIVERS_MATCHED D3 D1 D2" + ls +
                "RIDE_STARTED RIDE-001" + ls +
                "DRIVERS_MATCHED D6 D7 D5 D3 D4" + ls +
                "DRIVERS_MATCHED D5 D6 D7 D3" + ls +
                "RIDE_STOPPED RIDE-001" + ls +
                "RIDE_STARTED RIDE-002" + ls +
                "RIDE_STARTED RIDE-003" + ls +
                "BILL RIDE-001 D1 96.67" + ls +
                "RIDE_STOPPED RIDE-002" + ls +
                "RIDE_STOPPED RIDE-003" + ls +
                "BILL RIDE-003 D6 62.40" + ls +
                "BILL RIDE-002 D7 79.80";

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