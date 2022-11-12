package task;

import java.time.LocalDateTime;

import static formatting.Helper.FORMATTER;

public class Deadline extends Task{

    private LocalDateTime at;

    public LocalDateTime getAt() {
        return at;
    }
    @Override
    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    public Deadline(String taskName, LocalDateTime at) {
        super(taskName);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + isMarkAsDone() + "] " + TaskName + " (by: " + at.format(FORMATTER) + ")";
    }
}
