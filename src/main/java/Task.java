public class Task {
    protected String description;
    protected String type = "Task";
    protected String due;
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

    public String getDue() {
        return this.due == null? "" : this.due;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIsDone() {
        return this.isDone ? "1" : "0";
    }
}