package nus.duke.tasklist;

import nus.duke.task.*;
import nus.duke.storage.*;
import nus.duke.frontend.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList(ArrayList<Task> loadedTaskList){
        Collections.copy(this.taskList, loadedTaskList);
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    /*
    public TaskList() throws IOException {
        File f = new File("tasks.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred: Java's IOException");
            e.printStackTrace();
        }
    } */

    public static void viewTasks(){
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
