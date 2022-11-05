package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (this.by == null) {
            return "[D]" + super.toString();
        } else {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss")) + ")";
        }
    }
    
}
