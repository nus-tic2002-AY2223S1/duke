package nus.duke.task;

import java.util.ArrayList;

public abstract class Task {

    //private static int totalTasks = 0;

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public String getIsDone(){
        String isDoneStr = Boolean.toString(this.isDone);
        return isDoneStr;
    }

    public abstract String getTaskType();
}