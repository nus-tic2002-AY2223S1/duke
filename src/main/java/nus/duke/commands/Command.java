package nus.duke.commands;

import nus.duke.task.*;
import java.util.ArrayList;

public class Command {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void viewTasks(){
        System.out.println(" ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println( "[" + taskList.get(i).getTaskType() + "][" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getTask());
        }
    }

    public static void addTask(String userInput) {
        Task t;
        int idx = userInput.indexOf(" ") + 1;
        String task = userInput.substring(idx, userInput.length());
        if (userInput.contains("/by")){
            t = new Deadline(task);
        } else if (userInput.contains("/at")){
            t = new Event(task);
        } else {
            t = new Todo(task);
        }
        taskList.add(t);
        t.incrementTotalTasks();
        System.out.println("Added: " + task + ". ");
    }

    public static void deleteTask(int idx) {
        taskList.get(idx-1).decrementTotalTasks();
        taskList.remove(idx-1);
        System.out.println("Removed");
    }

    public static void markTask(int idx){ //mark as done
        (taskList.get(idx-1)).markAsDone();
        System.out.println("marked");
    }

    public static void unmarkTask(int idx){ //mark as done
        (taskList.get(idx-1)).markAsNotDone();
        System.out.println("unmarked");
    }
}
