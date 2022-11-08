package nus.duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a DoAfter task type extends from Task.
 */
public class DoAfter extends Task {

    private String taskBefore = null;
    private LocalDateTime dateAndTime;
    /**
     * Constructor for DoAfter task type with specific date.
     */
    public DoAfter(String description, LocalDateTime dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }
    /**
     * Constructor for DoAfter task type with specific task.
     */
    public DoAfter(String description, String taskBefore) {
        super(description);
        this.taskBefore = taskBefore;
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
        if (taskBefore != null){
            return "[A]" +  super.toString(1) + " (After" + ": " + this.taskBefore + ")";
        } else {
            return "[A]" + super.toString(1) + " (After" + ": " + this.getDateAndTime(mode) + ")";
        }
    }
}
