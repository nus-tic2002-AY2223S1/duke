package task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.symbol = "E";
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.at);
    }
}