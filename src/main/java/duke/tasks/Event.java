package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Deadline {
    protected LocalDate date;
    protected String time;

    public Event(String d) {
        super(d);
        int dateDiv = d.indexOf("/");
        description.replace(dateDiv+1, dateDiv+3, "at");
        String date = d.substring(dateDiv + 4, dateDiv + 14);
        String t1 = d.substring(dateDiv + 15);
        // String date = d.substring(dateDiv+5, dateDiv + 15);
        // String t1 = d.substring(dateDiv + 16);
        LocalDate d1 = LocalDate.parse(date);
        int length = d.length();
        setDate(d1);
        setTime(t1);
        description.replace(dateDiv, length, "(at: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + t1 + ")");
    }
};