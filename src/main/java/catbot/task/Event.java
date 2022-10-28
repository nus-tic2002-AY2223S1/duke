package catbot.task;

import catbot.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private TaskType taskType = TaskType.EVENT;

    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public Event(boolean isDone, String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.isDone = isDone;
        this.name = description;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getFromDateTime(){
        return fromDateTime;
    }

    public LocalDateTime getToDateTime(){
        return toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "-" + toDateTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}
