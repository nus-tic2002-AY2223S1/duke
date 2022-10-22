package nusduke.tasklist;

import nusduke.ui.Ui;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        }

public String getDescription() {
        return this.description;
        }
public String getDateAndTime() {
        return null;
        }
public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
        }

public void markAsDone() {
        this.isDone = true;
        Ui.print("Nice! I've marked this task as done:\n");
        Ui.print(Ui.getLinePrefix() + this.toString()+ "\n");
        }

public void markAsUndone() {
        this.isDone = false;
        Ui.print("OK, I've marked this task as not done yet:\n");
        Ui.print(Ui.getLinePrefix() + this.toString() + "\n");
        }

public void printTask(){
        System.out.print(this.toString() + "\n");
        }

public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
        }
public static void print(String toPrint){
        System.out.print(Ui.getLinePrefix() + toPrint);
        }
        //...
}
