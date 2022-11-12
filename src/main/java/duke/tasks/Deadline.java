package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

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
        String formatDate = "";
        String date = d.substring(dateDiv + 4, dateDiv + 14);
        if (date.contains("/")) {
            formatDate = date.replaceAll("/", "-");
        }
        String t1 = d.substring(dateDiv + 15);
        if (t1.contains("-")) {
            throw new DukeException(Ui.deadlineTimeError());
        } else {
            LocalDate d1;
            if (formatDate.isEmpty()) { 
                d1 = LocalDate.parse(date);
            } else {
                d1 = LocalDate.parse(formatDate);
            }
            int length = d.length();
            setDate(d1);
            setTime(t1);
            description.replace(dateDiv, length, 
                                "(by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + t1 + ")");
        }
    }

    // public void sortList(TaskList list, LocalDate d1, LocalDate d2) {
    //     for (int i = 1; i <= list.getCount(); i++) {
    //         list.getList().get(i-1).setTaskNo(i);
    //         System.out.println(i + ". " + list.getList().get(i-1).getDescription());
    //     }

    //     if (d1.isAfter(d2)) {
    //         Collections.swap(list, )
    //     }
    // }
};
