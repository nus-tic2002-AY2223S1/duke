package entities;
public class Task {
    private static String description;
    private static boolean isDone;

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
    public static String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void updateMark(boolean done) {
        this.isDone = done;
    }
//    public static String getTypeDescription(){
//        return "[" + type + "] " + "[" + getStatusIcon() + "] " + description;
//    }
}

