package Duke.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDate by;
    private DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by: " + by.format(formatDate) +" )";
    }

    @Override
    public String saveToFile() {
        int status = isDone ? 1:0;
        return "D | " + status + " | " + super.description + " | " + by + "\r";
    }
}
