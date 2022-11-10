import java.time.LocalDate;

public class Deadline extends Task {
    protected String my_by;
    protected LocalDate my_Deadline;

    public Deadline(String description, String tasktype, String by) {
        super(description, tasktype);
        my_by = by;
        my_Deadline = ParseDate(my_by);
        super.my_TaskDate = my_Deadline;
    }

    public void setDeadline(String date) {
        my_by = date;
        my_Deadline = ParseDate(my_by);
    }

    public String getDeadline() {
        return my_by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + my_by + ")";
    }
}