public class Event extends Task {
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getTask() {
        if(status) {
            return "[E][X] " + name + "(at: " + time + ")";
        }
        else {
            return "[E][ ] " + name  + "(at: " + time + ")";
        }
    }
}
