package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;
import duke.ui.Ui;

public class Event extends Deadline {

    public Event(String d) throws DukeException {
        super(d);
        int dateDiv = d.indexOf("/");
        description.replace(dateDiv+1, dateDiv+3, "at");
        
        String t1 = d.substring(dateDiv + 15);
        if (!t1.contains("-")) {
            throw new DukeException(Ui.eventTimeError());
        } else {
            // String date = d.substring(dateDiv+5, dateDiv + 15);
            // String t1 = d.substring(dateDiv + 16);
            String date = d.substring(dateDiv + 4, dateDiv + 14);
            LocalDate d1 = LocalDate.parse(date);
            int length = d.length();
            setDate(d1);
            setTime(t1);
            description.replace(dateDiv, length, "(at: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + t1 + ")");
        }
    }
};