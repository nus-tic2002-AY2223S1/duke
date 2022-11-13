package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    
    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description.toUpperCase());
        this.at = at;
    }

    public Event(String description) {
        super(description.toUpperCase());
    }

    @Override
    public String toString() {
        if (this.at == null) {
            return "[E]" + super.toString();
        } else {
            return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss")) + ")";
        }
    }

}
