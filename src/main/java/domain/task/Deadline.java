package domain.task;

import exceptions.EmptyDescriptionException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws EmptyDescriptionException {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}