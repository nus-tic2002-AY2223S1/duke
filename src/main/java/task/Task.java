package task;


public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTask() {
        return description;
    }

    public void mark() {
        isDone = true;
        //System.out.println("Nice! I've marked this task as done:\n [" + getStatusIcon() + "] " + getTask() + "\n");
    }

    public void unmark() {
        isDone = false;
        //System.out.println("OK, I've marked this task as not done yet:\n [" + getStatusIcon() + "] " + getTask() + "\n");
    }

    public String toOutput() {
        return (description);
    }

    public String toString() {

        return "[" + getStatusIcon() + "] " + description;
    }
}
