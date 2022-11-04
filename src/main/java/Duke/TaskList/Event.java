package Duke.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    private LocalTime start, end;
    private DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    private DateTimeFormatter displayTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
    private DateTimeFormatter saveDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter saveTimeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public Event(String description, LocalDate date, LocalTime start, LocalTime end, boolean isDone) {
        super(description);
        this.date = date;
        this.start = start;
        this.end = end;
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " ( at: " + date + start + end + " ) ";
    }

    @Override
    public String saveToFile() {
        int status = isDone ? 1:0;
        return "E | " + status + " | " + super.description;
    }
}
