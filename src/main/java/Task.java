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
        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
        System.out.println("[X]" + this.description);
    }

    public void markAsNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;
        System.out.println("[ ]" + this.description);
    }
}
