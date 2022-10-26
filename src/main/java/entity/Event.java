package entity;

import entity.Task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toFile() {
        return "E" + " | " + getStatus() + " | " + description + " | " + at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
