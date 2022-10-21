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
        if (getIsDone().equals(false)){
            this.isDone = true;
        } else {
            System.out.println("Task is already marked done");
        }
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


    //public String formatDateTime(String userInput){
        //String dateInString = getDate(userInput);
        //LocalDate date = LocalDate.parse(dateInString);
        //System.out.println(date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))); // -> Oct 15 2019
    //}
}