package Duke.TaskList;
import java.util.ArrayList;
import Duke.*;
import Duke.Exception.DukeException;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static ArrayList<Task> taskList;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public static ArrayList<Task> getList (){
        return taskList;
    }

    public void markAsDone(boolean status){
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String saveToFile();
}
