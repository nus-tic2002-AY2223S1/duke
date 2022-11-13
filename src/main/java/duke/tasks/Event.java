package duke.tasks;

import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.ui.Ui;

public class Event extends Deadline {

    public Event(String d) throws DukeException {
        super(d);
        int dateDiv = d.indexOf("/");
        description.replace(dateDiv+1, dateDiv+3, "at");
        
        String t1 = d.substring(dateDiv + 15);
        //checks if event consist of a start and end time.
        if (!t1.contains("-")) {
            throw new DukeException(Ui.eventTimeError());
        } else {
            String formatDate = "";
            String date = d.substring(dateDiv + 4, dateDiv + 14);
            if (date.contains("/")) {
                formatDate = date.replaceAll("/", "-");
            }
            assert date == "\\d{4}-\\d{2}-\\d{2}";
            LocalDate d1;
            if (formatDate.isEmpty()) { 
                d1 = LocalDate.parse(date);
            } else {
                d1 = LocalDate.parse(formatDate);
            }
            setDate(d1);
            setTime(t1);
        }
    }
};