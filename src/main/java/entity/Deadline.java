package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Deadline object constructor
     *
     * @param description inputs description by user
     * @param by          inputs datetime by user
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Parse To-do object to string
     *
     * @return a string to be saved to text file
     */
    @Override
    public String toFile() {
        return "D" + " | " + getStatus() + " | " + getTags() + " | " + description + " | " + by + "\n";
    }

    /**
     * Parse To-do object to string
     *
     * @return a string of To-do task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}