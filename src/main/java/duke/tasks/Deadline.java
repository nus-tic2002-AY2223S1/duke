package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public Deadline(String d) {
        super(d);
        int dateDiv = d.indexOf("/");
        String date = d.substring(dateDiv + 4, dateDiv + 14);
        String t1 = d.substring(dateDiv + 15);
        LocalDate d1 = LocalDate.parse(date);
        int length = d.length();
        setDate(d1);
        setTime(t1);
    }
};
