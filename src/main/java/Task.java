public class Task {
    protected static String description;
    protected static boolean isDone;
    protected static char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
    public String getStatusDescription() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public void setType(char t){
        this.type = t;
    }
    public static char getType() {
        return type;
    }
    public static String getTypeDescription(){
        return "[" + type + "] " + "[" + getStatusIcon() + "] " + description;
    }
}
