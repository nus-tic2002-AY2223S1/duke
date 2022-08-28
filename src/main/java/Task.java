public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Get statusIcon of task
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    // Return task description
    public String getDescription(){
        return this.description;
    }

    // Set task done
    public void markDone() {
        this.isDone = true;
    }

    // Set task not done
    public void unMarkDone() {
        this.isDone = false;
    }
}