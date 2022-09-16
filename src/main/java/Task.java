public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public void markAsUndone() { isDone = false;
        System.out.println(Duke.line + "OK, I've marked this task as not done yet.");
        System.out.println("[ ] " + description + Duke.line);
    }

    public void markAsDone() {
        isDone = true;
        System.out.println(Duke.line + "Nice! I've marked this task as done:");
        System.out.println("[X] " + description + Duke.line);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
