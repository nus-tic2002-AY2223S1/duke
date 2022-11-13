package domain.task;

import exceptions.EmptyDescriptionException;

import java.time.LocalDate;

import static utils.Formatter.dateOutputFormat;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) throws EmptyDescriptionException {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    public String getFormattedByDate() {
        return this.by.format(dateOutputFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedByDate() + ")";
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }
}