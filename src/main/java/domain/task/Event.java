package domain.task;

import exceptions.EmptyDescriptionException;

public class Event extends Task{

    protected String at;

    public Event(String description, String at) throws EmptyDescriptionException {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
