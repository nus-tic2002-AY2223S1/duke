public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //get method
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //set method
    public void markAsDone() {
        this.isDone = true;
    }
}
