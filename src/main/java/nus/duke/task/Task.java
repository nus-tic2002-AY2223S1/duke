package nus.duke.task;

import java.util.ArrayList;

public abstract class Task {

    // public classes so that they can be accessed by functions in commands class
    private static int totalTasks = 0;
    //public static ArrayList<Task> wishList = new ArrayList<Task>();

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void incrementTotalTasks(){
        totalTasks = totalTasks + 1;
    }
    public void decrementTotalTasks(){
        totalTasks = totalTasks - 1;
    }

    public String getTask(){
        return this.description;
    }

    public void markAsDone(){
        this.isDone = true;
    }
    public void markAsNotDone(){
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public abstract String getTaskType();
}