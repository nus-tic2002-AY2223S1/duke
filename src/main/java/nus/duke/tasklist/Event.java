package nus.duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private String preposition;
    private LocalDateTime dateAndTime1;
    private LocalDateTime dateAndTime2;
    /**
     * Constructor for Event task type.
     */
    public Event(String description, String preposition,LocalDateTime dateAndTime1, LocalDateTime dateAndTime2) {
        super(description);
        this.preposition = preposition;
        this.dateAndTime1 = dateAndTime1;
        this.dateAndTime2 = dateAndTime2;
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
        String formatDateTime1 = this.dateAndTime1.format(format);
        String formatDateTime2 = this.dateAndTime2.format(format);
        String formatDateTime = formatDateTime1 + "-" + formatDateTime2;
        return formatDateTime;
    }
    @Override
    public String toString(int mode) {
        return "[E]" +  super.toString(1) + " (" + preposition + ": " + this.getDateAndTime(mode) + ")";
    }

}