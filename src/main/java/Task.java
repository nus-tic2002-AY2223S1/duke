public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
