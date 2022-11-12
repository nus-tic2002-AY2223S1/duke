package tasks;

import tasks.Task;

public class Deadline extends Task {
    protected String due;

    public Deadline(String name, String due) {
        super(name);
        this.due = due;
    }

    @Override
    public String getTask() {
        if(isDone) {
            return "D|1|" + name + "|" + due + "|";
        } else {
            return "D|0|" + name  + "|" + due + "|";
        }
    }
}
