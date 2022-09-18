package entity;

public class Event extends Task {

    private String time;
//    private String endTime;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String getDetails() {
        return String.format("%s%s %s (%s)", getTypeIcon(), getStatusIcon(), getDescription(), getTime());
    }
}
