package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class Deadline extends Task{

    private Date at;

    public Deadline(String taskName) {
        super(taskName);
    }
    public Deadline(String taskName, Date at) {
        super(taskName);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + isMarkAsDone() + "] " + TaskName + " (by: " + at + ")";
    }
}
