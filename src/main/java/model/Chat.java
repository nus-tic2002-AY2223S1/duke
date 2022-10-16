package model;

import common.enums.CommandEnum;

import java.util.ArrayList;

public class Chat {
    protected static String input;

    protected static CommandEnum command;
    protected static ArrayList<Task> taskList = new ArrayList<>();

    public Chat(CommandEnum command, String input, ArrayList<Task> taskList) {
        this.command = command;
        this.input = input;
        this.taskList = taskList;
    }

    public static CommandEnum getCommand() {
        return command;
    }

    public static String getInput() {
        return input;
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
