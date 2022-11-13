package tasklist;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }


    // mark done task with X
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // return isDone is true if task is "mark" and print message
    public void markAsDone() {
        this.isDone = true;
    }

    // return isDone is false if task is "unmark" and print message
    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusOutput() {
        if (this.getStatusIcon().equals("X"))
            return "1";
        else return "0";
    }

    // string to print
    public String toString() {
        return "[" + this.getStatusIcon() + "]   " + this.getDescription();
    }

    public String toOutput() {
        return ";" + this.getStatusOutput() + ";" + this.getDescription();
    }

    // get date for event and deadline
    public LocalDate getDate() {
        return null;
    }

}