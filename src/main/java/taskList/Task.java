package tasklist;

import java.time.LocalDate;

import static ui.TaskMessages.*;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }


    // mark done task with X
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // return isDone is true if task is "mark" and print message
    public void markAsDone(){
        this.isDone=true;
        ui.UI.printLine();
        ui.UI.printMessage(MESSAGE_MARKED_TASK + this.getDescription());
        ui.UI.printLine();
    }

    // return isDone is false if task is "unmark" and print message
    public void markAsUndone(){
        this.isDone=false;
        ui.UI.printLine();
        ui.UI.printMessage(MESSAGE_UNMARKED_TASK + this.getDescription());
        ui.UI.printLine();
    }

    // string to print
    public String toString() {
        return "[" + this.getStatusIcon() + "]   " + this.getDescription();
    }

    // get date for event and deadline
    public LocalDate getDate() { return null; }
}