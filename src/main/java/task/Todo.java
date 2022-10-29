package task;

import java.util.ArrayList;

public class Todo extends Task{


    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + isMarkAsDone() + "] " + TaskName;
    }
}
