package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chat {
    protected static Scanner userInput;
    protected static String input;

    protected static String command;
    protected static List<Task> taskList = new ArrayList<>();

    public Chat(Scanner userInput, String command, String input, List<Task> taskList) {
        this.userInput = userInput;
        this.command = command;
        this.input = input;
        this.taskList = taskList;
    }

    public static Scanner getUserInput() {
        return userInput;
    }

    public static String getCommand() {
        return command;
    }

    public static String getInput() {
        return input;
    }

    public static List<Task> getTaskList() {
        return taskList;
    }
}
