package entities;
public class Task {
    private String description;
    protected boolean isDone;
    protected char type;
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

    public char getType() {
        return type;
    }

    public String toCommandString(){
        return " | " + (this.getStatusIcon() == "X" ? "1" : "0") + " | " + this.getDescription();
    }
}

