package nus.duke.tasklist;

import nus.duke.ui.Ui;
/**
 * Represents a task.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        }
    /**
     * Getter for the Task description.
     */
    public String getDescription() {
        return this.description;
        }
    /**
     * Getter for the date and time for deadline and event.
     */
    public String getDateAndTime(int mode) {
        return null;
        }
    /**
     * Getter for the marking status.
     *
     * @return "X" or " " depends on the value of boolean isDone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
        }
    /**
     * Mark the task as done.
     */
    public void markAsDone(int mode) {
        this.isDone = true;
        if (mode == 0) {
            Ui.print("Nice! I've marked this task as done:\n");
            Ui.print(Ui.getLinePrefix() + this.toString(1) + "\n");
        }
    }
    /**
     * Mark the task as undone.
     */
    public void markAsUndone(int mode) {
        this.isDone = false;
        if(mode == 0) {
            Ui.print("OK, I've marked this task as not done yet:\n");
            Ui.print(Ui.getLinePrefix() + this.toString(1) + "\n");
        }
    }

    public void printTask(){
        System.out.print(toString(1) + "\n");
        }
    /**
     * Format task for displaying purpose.
     */
    public String toString(int mode){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
