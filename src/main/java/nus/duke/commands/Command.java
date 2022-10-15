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

    public static void addTask(String wish) {
        Task t;
        if (wish.contains("/by")){
            t = new Deadline(wish);
        } else if (wish.contains("/at")){
            t = new Event(wish);
        } else {
            t = new Todo(wish);
        }
        taskList.add(t);
        t.incrementTotalTasks();
        System.out.println("Added: " + wish + ". ");
    }

    public static void deleteTask(String wish) {
        int wNumber = Integer.parseInt(wish);
        taskList.get(wNumber-1).decrementTotalTasks();
        taskList.remove(wNumber-1);
        System.out.println("Removed: " + wish);
        System.out.println();
    }

    public static void markTask(String wish){ //mark as done
        int wNumber = Integer.parseInt(wish);
        (taskList.get(wNumber-1)).markAsDone();
        System.out.println("marked: " + wish);
        System.out.println();
    }

    public static void unmarkTask(String wish){ //mark as done
        int wNumber = Integer.parseInt(wish);
        (taskList.get(wNumber-1)).markAsNotDone();
        System.out.println("unmarked: " + wish);
        System.out.println();
    }
}
