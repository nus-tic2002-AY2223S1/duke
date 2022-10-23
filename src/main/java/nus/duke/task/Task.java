package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        if (this.isDone == false){
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
    public abstract String getTaskDetails();

    public String getDescription(String userInput){
        int end = 0;
        if (userInput.indexOf("/by") != -1){
            end = userInput.indexOf("/by");
            return userInput.substring(0, end);
        }

        if (userInput.indexOf("/at") != -1){
            end = userInput.indexOf("/at");
            return userInput.substring(0, end);
        }
         return "";
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDateInStr(String userInput){
        int start = userInput.indexOf("/by") + 4;
        if (userInput.indexOf("[T]") == -1 || userInput.indexOf("[F]") == -1){
            return userInput.substring(start, userInput.length()).trim();
        } else {
            return userInput.substring(start, userInput.length()-3).trim();
        }
    }

    public LocalDate processDate(String userInput){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = getDateInStr(userInput);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}