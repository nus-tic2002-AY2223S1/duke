package nus.duke.tasklist;

import nus.duke.task.*;
import nus.duke.storage.*;
import nus.duke.frontend.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import nus.duke.frontend.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    private static int totalTasks = 0;

    public TaskList(ArrayList<Task> loadedTaskList){
        Collections.copy(loadedTaskList,this.taskList);
    }
    public TaskList(){};

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    public void incrementTotalTasks(){
        this.totalTasks = this.totalTasks + 1;
    }
    public void decrementTotalTasks(){
        this.totalTasks = this.totalTasks - 1;
    }
    public int getTotalTasks(){ return this.totalTasks; }

    public static int getItemNumber(String userInput){
        int idx = userInput.indexOf(" ");
        String itemNumber = userInput.substring(idx+1, userInput.length());
        idx = Integer.parseInt(itemNumber);
        return idx;
    }

    public static void viewTasks(){
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println( "[" + taskList.get(i).getTaskType() + "][" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getTask() + taskList.get(i).getTaskDetails());
        }
    }

    public void addTask(String userInput) {
        Task t;
        if (userInput.contains("/by")){
            t = new Deadline(userInput);
        } else if (userInput.contains("/at")){
            t = new Event(userInput);
        } else {
            t = new Todo(userInput);
        }
        taskList.add(t);
        incrementTotalTasks();
        System.out.println("Added: " + userInput + ". ");
    }

    public void deleteTask(int idx) {
        taskList.remove(idx-1);
        decrementTotalTasks();
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

    public boolean processTasks(String command, String userInput){
        int end = userInput.indexOf("[");
        String parsedString = userInput;

        if (end != -1){
            parsedString = userInput.substring(0, end);
        }

        if (command.equals("EXIT")){
            return true;
        }

        if ((command.equals(LegalCommandEnumerations.VIEW.toString())) && ((this.getTotalTasks()) == 0)){
            System.out.println("There are 0 tasks in your list.");
            return false;
        }
        if ((command.equals(LegalCommandEnumerations.VIEW.toString())) && ((this.getTotalTasks()) > 0)){
            this.viewTasks();
            return false;
        }

       if (command.equals(LegalCommandEnumerations.MARK.toString())) {
           this.markTask(getItemNumber(userInput));
           return false;
       }

       if (command.equals(LegalCommandEnumerations.UNMARK.toString())) {
           this.unmarkTask(getItemNumber(userInput));
           return false;
       }

       if (command.equals(LegalCommandEnumerations.DELETE.toString())){
           this.deleteTask(getItemNumber(userInput));
           return false;
       }

       this.addTask(parsedString);
       return false;
    }

    public void processIsDone(int idx, String userInput){
        int start = userInput.indexOf("[") + 1;
        String isDone = userInput.substring(start, userInput.length()-1);
        if (isDone.equals("T")){
            this.markTask(idx);
        }
    }

}
