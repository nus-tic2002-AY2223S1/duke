import java.time.LocalDate;

public class Event extends Task {
    protected String my_by;
    protected LocalDate my_Event;

    public Event(String description, String taskType, String by) {
        super(description, taskType);
        this.my_by = by;
        my_Event = ParseDate(my_by);
        super.my_TaskDate = my_Event;
    }

    public void setEvent(String date) {
        my_by = date;
        my_Event = ParseDate(my_by);
    }

    public String getEvent() {
        return my_by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + my_by + ")";
    }
}
