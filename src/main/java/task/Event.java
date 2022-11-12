package task;

import java.text.ParseException;
import java.time.LocalDateTime;

import static formatting.Helper.FORMATTER;

public class Event extends Task{

    private LocalDateTime start;
    private LocalDateTime end;
    public Event(String taskName, LocalDateTime start, LocalDateTime end) throws ParseException {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + isMarkAsDone() + "] " + TaskName +  " (at: " + start.format(FORMATTER) + " to " + end.format(FORMATTER) +  ")";
    }

}
