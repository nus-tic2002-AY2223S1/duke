package entity;

import entity.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Event task constructor
     *
     * @param description inputs description by user
     * @param at          inputs datetime by user
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }
    public LocalDateTime getAt() {
        return at;
    }
    /**
     * Parse To-do object to string
     *
     * @return a string to be saved to text file
     */
    @Override
    public String toFile() {
        return "E" + " | " + getStatus() + " | "  + getTags() + " | " + description + " | " + at + "\n";
    }

    /**
     * Parse To-do object to string
     *
     * @return a string of To-do task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
