package duke.form;

import java.time.LocalDateTime;

public class EventForm extends TodoForm {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public EventForm(String metaData, String command, String description) {
        super(metaData, command, description);
    }

    public EventForm(String metaData, String command, String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(metaData, command, description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
