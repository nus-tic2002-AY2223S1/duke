package tasks;

import tasks.Task;

public class Event extends Task {
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getTask() {
        if(isDone) {
            return "E|1|" + name + "|" + time + "|";
        } else {
            return "E|0|" + name  + "|" + time + "|";
        }
    }
}
