package task;

import java.time.LocalDateTime;

import static formatting.Helper.formatter;

public class Deadline extends Task{

    private LocalDateTime at;

    public Deadline(String taskName, LocalDateTime at) {
        super(taskName);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + isMarkAsDone() + "] " + TaskName + " (by: " + at.format(formatter) + ")";
    }
}
