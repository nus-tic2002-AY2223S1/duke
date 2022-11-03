package entities;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void updateMark(boolean done) {
        this.isDone = done;
    }

}

