package entity;

import parser.DatetimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    
    private LocalDateTime time;
    
    /**
     * Event Constructor
     *
     * @param description task description
     * @param time        event time
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }
    
    /**
     * Event Constructor
     *
     * @param description task description
     * @param time        event time
     */
    public Event(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }
    
    /**
     * Return event task time
     *
     * @return time variable
     */
    public String getTime() {
        return DatetimeParser.parseDateTimeToString(time);
    }
    
    /**
     * Set event task time
     *
     * @param time time that user inputted
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    
    /**
     * Return task type indicator
     *
     * @return type icon of event task
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }
    
    /**
     * Return event task detail in string type with specific format
     *
     * @return event task details
     */
    @Override
    public String getDetails() {
        return String.format("[%s]%s %s (at: %s)", getTypeIcon(), getStatusIcon(), getDescription(), getTime());
    }
    
    /**
     * Return event task details for saving locally
     *
     * @return event task details with saving format
     */
    @Override
    public String getSavingFormatDetails() {
        return String.format("%s | %d | %s | %s\n", getTypeIcon(), isDone() ? 1 : 0, getDescription(), getTime());
    }
}
