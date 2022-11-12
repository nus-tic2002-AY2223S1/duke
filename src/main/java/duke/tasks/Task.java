package duke.tasks;

public class Task {
    protected StringBuilder description;
    protected boolean isDone;

    public Task(String d) {
        this.description = new StringBuilder(d);
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return description.toString();
    }
}
