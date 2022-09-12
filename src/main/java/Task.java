public class Task {
    protected String description;
    protected boolean isDone;
    protected String myTaskType;

    //constructor
    public Task(String description) {
        this.description = description;
        this.myTaskType = description.substring(0);
    }
    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
        System.out.println("[X] " + description);
    }

    public void markAsNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;
        System.out.println("[] " + description);
    }

    //get method
    public String getTaskTypeIcon() {
        return myTaskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString(){
        return "[" + getStatusIcon() + "] "  + description;
    }
}
