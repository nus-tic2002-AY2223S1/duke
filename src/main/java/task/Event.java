package task;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Event extends Task{

    private Date start;
    private Date end;
    public Event(String taskName, Date start, Date end) throws ParseException {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + isMarkAsDone() + "] " + TaskName +  " (at: " + start + " to " + end +  ")";
    }

}
