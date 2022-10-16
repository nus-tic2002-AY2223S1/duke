import java.io.IOException;

abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String myTaskType;

    //constructor
    public Task(String description, String TaskType) {
        this.description = description;
        this.myTaskType = TaskType;
    }

    private static void markTaskFromFile(String filePath) throws IOException {
        //replace the task in the file to true
    }

    private static void unmarkTaskFromFile(String filePath) throws IOException {
        //replace the task in the file to true
    }

    public void markAsDone() throws IOException {
        //mark true in tasks.txt
        markTaskFromFile("data/tasks.txt");

        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
        System.out.println("[X] " + description);
    }

    public void markAsNotDone() throws IOException {
        //mark false in tasks.txt
        unmarkTaskFromFile("data/tasks.txt");

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
