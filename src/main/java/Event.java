import java.util.ArrayList;
import java.util.Date;

public class Event extends Task{
    private String atDate;
    public Event(String taskName, String atDate) {
        super(taskName);
        this.atDate = atDate;
    }
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDate + ")";
    }

}
