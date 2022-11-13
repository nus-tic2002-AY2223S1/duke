package nus.duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a Deadline task type extends from Task.
 */
public class Deadline extends Task {

    private String preposition;
    private LocalDateTime dateAndTime;
    /**
     * Constructor for Deadline task type.
     */
    public Deadline(String description, String preposition, LocalDateTime dateAndTime) {
        super(description);
        this.preposition = preposition;
        this.dateAndTime = dateAndTime;
    }
    public String getDescription() {
        return super.getDescription();
    }
    @Override
    public String getDateAndTime(int mode) {
        DateTimeFormatter format;
        switch (mode) {
            case 1:
                format = TaskList.DISPLAY_FORMATTER;
                break;
            case 2:
                format = TaskList.STORAGE_FORMATTER;
                break;
            default:
                format = DateTimeFormatter.ISO_DATE_TIME;

        }
        String formatDateTime = this.dateAndTime.format(format);
        return formatDateTime;
    }
    @Override
    public String toString(int mode) {
        return "[D]" +  super.toString(1) + " (" + preposition + ": " + this.getDateAndTime(mode) + ")";
    }
}
