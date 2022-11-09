package duke.tasks;

public class Event extends Deadline {
    protected String time;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public Event(String d) {
        super(d);
        int by = description.lastIndexOf("by");
        description.replace(by, by+2, "at");
    }
};