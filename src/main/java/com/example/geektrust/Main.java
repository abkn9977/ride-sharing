package com.example.geektrust;

import com.example.geektrust.appconfig.AppConfig;
import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.exceptions.NoSuchCommandException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class Main {
    // To run the application ./gradlew run --args="sample_input\input1.txt"
    public static void main(String[] args) {
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        if(commandLineArgs.size() > 0)
            run(commandLineArgs);
    }

    public static void run(List<String> commandLineArgs) {

        AppConfig appConfig = new AppConfig();
        CommandInvoker commandInvoker = appConfig.getCommandInvoker();

        BufferedReader reader;
        String input = commandLineArgs.get(0);

        try{
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while(line != null){
                List<String> tokens = Arrays.asList(line.split(" "));

                //execute command
                commandInvoker.executeCommand(tokens.get(0), tokens);

                line = reader.readLine();
            }

        } catch (NoSuchCommandException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}