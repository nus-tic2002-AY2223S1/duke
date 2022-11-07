package entity;

import parser.DatetimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    
    private LocalDateTime deadline;
    
    /**
     * Deadline Constructor
     *
     * @param description task description
     * @param deadline    task deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    
    /**
     * Deadline Constructor
     *
     * @param description task description
     * @param deadline    task deadline
     * @param isDone      indicate if the task is marked as done
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }
    
    /**
     * Return deadline task deadline
     *
     * @return deadline variable
     */
    public String getDeadline() {
        return DatetimeParser.parseDateTimeToString(deadline);
    }
    
    /**
     * Set deadline task deadline
     *
     * @param deadline deadline that user inputted
     */
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    
    /**
     * Return task type indicator
     *
     * @return type icon of deadline task
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }
    
    /**
     * Return deadline task detail in string type with specific format
     *
     * @return deadline task details
     */
    @Override
    public String getDetails() {
        return String.format("[%s]%s %s (by: %s)", getTypeIcon(), getStatusIcon(), getDescription(), getDeadline());
    }
    
    /**
     * Return deadline task details for saving locally
     *
     * @return deadline task details with saving format
     */
    @Override
    public String getSavingFormatDetails() {
        return String.format("%s | %d | %s | %s\n", getTypeIcon(), isDone() ? 1 : 0, getDescription(), getDeadline());
    }
}
