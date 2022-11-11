
package parser;

import tasklist.Deadline;
import tasklist.Event;
import tasklist.Fixed;
import tasklist.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Parser {

    // try to add the commands here to parse
    // update storage to kt way

    // format todo input before it is added
    public static Todo parseTodoInput(String input) {
        String todoTask = input.substring(5);
        return new Todo(todoTask);
    }

    // format deadline input before it is added
    public static Deadline parseDeadlineInput(String input) {
        String[] deadlineItemSplit = (input.substring(9)).split(" /by ");
        String deadlineTask = deadlineItemSplit[0];
        String deadlineBy = deadlineItemSplit[1];
        return new Deadline(deadlineTask, deadlineBy);
    }

    // format event input before it is added
    public static Event parseEventInput(String input){
        String[] eventItemSplit = (input.substring(6)).split(" /at ");
        String eventTask = eventItemSplit[0];
        String eventAt = eventItemSplit[1];
        return new Event(eventTask, eventAt);
    }
    public static String parseScheduleInput(String input){
        String[] scheduleDateSplit = input.split(" ");
        String scheduleDate = scheduleDateSplit[2];
        return scheduleDate;
    }

    public static String parseFindInput(String input){
        return input.substring(5);
    }

    public static Fixed parseFixedDurationInput (String input){
        String[] fixedItemSplit = (input.substring(5)).split(" /needs ");
        String fixedTask = fixedItemSplit [0];
        String fixedNeeds = fixedItemSplit[1];
        return new Fixed(fixedTask, fixedNeeds);
    }

    // convert input date to LocalDate format
    public static LocalDate StringToDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        return LocalDate.parse(s, formatter);
    }

    // convert LocalDate date to String format
    public static String DateToString (LocalDate d){
        return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}