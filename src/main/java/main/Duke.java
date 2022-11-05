package main;

import exception.EmptyActionException;
import exception.IllegalActionException;
import exception.IllegalContentException;
import manager.CommandManager;
import util.CommandType;

public class Duke {
    
    public static String[] inputArr; //["action", "content"]
    public static CommandManager commandManager;
    
    public Duke() {
        commandManager = CommandManager.getInstance();
    }
    
    public void getUserInput(String input) throws IllegalActionException, EmptyActionException {
        if (input.isEmpty()) {
            throw new EmptyActionException();
        }
        
        inputArr = input.split(" ", 2);
        inputArr[0] = inputArr[0].toLowerCase();
        if (!CommandType.contains(inputArr[0])) {
            throw new IllegalActionException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String executionResult = "";
        try {
            getUserInput(input);
            executionResult = commandManager.executeUserInput(inputArr);
            
        } catch (IllegalActionException | EmptyActionException | IllegalContentException e) {
                executionResult = e.getMessage();
        }
    
        return executionResult;
    }
    
    public String getGreetingMessage(){
        return "Hello! I'm main.Duke\n \t What can I do for you?";
    }
}
