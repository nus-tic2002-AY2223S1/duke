package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Chat {
    protected static String input;

    protected static String command;
    protected static ArrayList<Task> taskList = new ArrayList<>();

    public Chat(String command, String input, ArrayList<Task> taskList) {
        this.command = command;
        this.input = input;
        this.taskList = taskList;
    }

    public static String getCommand() {
        return command;
    }

    public static String getInput() {
        return input;
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
