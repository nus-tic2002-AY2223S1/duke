package model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    protected static String input;
    protected static List<Task> taskList = new ArrayList<>();

    public Chat(String input, List<Task> taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    public static String getInput() {
        return input;
    }

    public static List<Task> getTaskList() {
        return taskList;
    }
}
