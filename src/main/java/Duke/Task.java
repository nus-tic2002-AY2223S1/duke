package Duke;

import Util.DateProcessor;

public class Task {
    protected String description;
    protected String type = "Task";
    protected long due = 0;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //Assumes users will repeatedly call mark / unmark, so we cannot just invert the value
    public void markTask() {
        this.isDone = true;
    }
    public void unmarkTask() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getType() {
        return this.type;
    }

    public long getDue() {
        return this.due;
    }
    public String getDescription() {
        return this.description;
    }

    public String getIsDone() {
        return this.isDone ? "1" : "0";
    }
}