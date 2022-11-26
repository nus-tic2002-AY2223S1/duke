package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

public class Deadline extends Todo {

    protected LocalDate date;
    protected String time;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    /**
    * Creates a new Deadline with the given string.
    * The string should include the task brackets, description, date and time.
    */
    public Deadline(String d) throws DukeException {
        super(d);
        int dateDiv = d.indexOf("/");
        String formatDate = "";
        String date = d.substring(dateDiv + 4, dateDiv + 14);
        if (date.contains("/")) {
            formatDate = date.replaceAll("/", "-");
        }
        assert date == "\\d{4}-\\d{2}-\\d{2}";
        String t1 = d.substring(dateDiv + 15);
        LocalDate d1;
        if (formatDate.isEmpty()) { 
            d1 = LocalDate.parse(date);
        } else {
            d1 = LocalDate.parse(formatDate);
        }
        //assertFalse(d1.toString().isEmpty());
        int length = d.length();
        setDate(d1);
        setTime(t1);
        description.replace(dateDiv, length, 
                            "(by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + t1 + ")");
    }
};
