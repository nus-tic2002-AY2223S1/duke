
package parser;

import taskList.Deadline;
import taskList.Event;
import taskList.Todo;
import ui.UI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Parser {

    // format todo input before it is added
    public static Todo parseTodoInput(String input) {
        String todoTask = input.substring(5);
        Todo task = new Todo(todoTask);
        return task;
    }

    // format deadline input before it is added
    public static Deadline parseDeadlineInput(String input) {
        String[] deadlineItemSplit = (input.substring(9)).split(" /by ");
        String deadlineTask = deadlineItemSplit[0];
        String deadlineBy = deadlineItemSplit[1];
        Deadline task = new Deadline(deadlineTask, deadlineBy);
        return task;
    }

    // format event input before it is added
    public static Event parseEventInput(String input){
        String[] eventItemSplit = (input.substring(6)).split(" /at ");
        String eventTask = eventItemSplit[0];
        String eventAt = eventItemSplit[1];
        Event task = new Event(eventTask, eventAt);
        return task;
    }
    public static String parseScheduleInput(String input){
        String[] scheduleDateSplit = input.split(" ");
        String scheduleDate = scheduleDateSplit[2];
        return scheduleDate;
    }

    public static String parseFindInput(String input){
        String findInput = input.substring(5);
        return findInput;
    }

    // convert input date to LocalDate format
    public static LocalDate StringToDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(s, formatter);
        return date;
    }

    // convert LocalDate date to String format
    public static String DateToString (LocalDate d){
        String string = d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return string;
    }
}