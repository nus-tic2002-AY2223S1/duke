package tasks;

import tasks.Task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String getTask() {
        if(isDone) {
            return "T|1|" + name;
        } else {
            return "T|0|" + name;
        }
    }
}
