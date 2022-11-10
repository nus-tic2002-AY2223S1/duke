import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String my_by;
    protected LocalDate my_Deadline;

    public Deadline(String description, String tasktype, String by) {
        super(description, tasktype);
        my_by = by;
        my_Deadline = ParseDate(my_by);
    }

    public void setDeadline(String date) {
        my_by = date;
    }

    public String getDeadline() {
        return my_by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + my_by + ")";
    }
}