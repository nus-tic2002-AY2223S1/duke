package task;
import java.util.ArrayList;
import java.util.Date;

public class Event extends Task{
    private String atDate;
    public Event(String taskName, String atDate) {
        super(taskName);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + isMarkAsDone() + "] " + TaskName +  " (at: " + atDate + ")";
    }

}
