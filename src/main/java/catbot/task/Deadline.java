package catbot.task;

import catbot.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime byDateTime;
    private TaskType taskType = TaskType.DEADLINE;

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    public Deadline(boolean isDone, String description, LocalDateTime byDateTime) {
        this.isDone = isDone;
        this.name = description;
        this.byDateTime = byDateTime;
    }

    public LocalDateTime getBy() {
        return byDateTime;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
