public class Task {
    protected StringBuilder description;
    protected boolean isDone;
    private static int taskCount = 0;

    public Task(String d) {
        this.description = new StringBuilder(d);
        this.isDone = false;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println(description);
        System.out.println("Now you have " + taskCount + " in the list.");
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

    public int getTaskCount() {
        return taskCount;
    }
}
