package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;
import duke.ui.Ui;

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

    public Deadline(String d) throws DukeException {
        super(d);
        int dateDiv = d.indexOf("/");
        String date = d.substring(dateDiv + 4, dateDiv + 14);
        String t1 = d.substring(dateDiv + 15);
        if (t1.contains("-")) {
            throw new DukeException(Ui.deadlineTimeError());
        } else {
            LocalDate d1 = LocalDate.parse(date);
            int length = d.length();
            setDate(d1);
            setTime(t1);
            description.replace(dateDiv, length, "(by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + t1 + ")");
        }
    }
};
