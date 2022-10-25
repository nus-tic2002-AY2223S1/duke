package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask(){
        return this.description;
    }

    public void markAsDone() {
        if (this.isDone == false) {
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

    public String getIsDone() {
        String isDoneStr = Boolean.toString(this.isDone);
        return isDoneStr;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public LocalDate processDate(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = getDateInStr(userInput);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public abstract String getDateInStr(String userInput);
    public abstract String getTaskType();
    public abstract String getTaskDetails();
    public abstract String getDescription(String userInput);
}