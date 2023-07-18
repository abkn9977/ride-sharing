package com.example.geektrust.commands;

import com.example.geektrust.exceptions.NoSuchCommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, ICommand> commandMap = new HashMap<>();

    //register command to map
    public void register(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }

    //get command by command Name
    public ICommand get(String commandName){
        return commandMap.get(commandName);
    }

    //execute command
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        ICommand command = get(commandName);

        if(command == null)
            throw new NoSuchCommandException();

        command.execute(tokens);
    }
}