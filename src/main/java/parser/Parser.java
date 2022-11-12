
package parser;

import tasklist.*;
import ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Parser {

    public static void parse(String input, TaskList newTaskList){
        String[] inputSplit = input.split(" ");

        // enter bye to end chat
        if (input.equals("bye"))
            newTaskList.exit();

            // to list all items
        else if (input.equals("list"))
            newTaskList.listTask(inputSplit, input);

            // mark items
        else if (input.startsWith("mark"))
            newTaskList.markTask(inputSplit, input);

            // unmarked items
        else if (input.startsWith("unmark"))
            newTaskList.unmarkTask(inputSplit, input);

            // to do task
        else if (input.startsWith("todo"))
            newTaskList.todoTask(input, inputSplit);

            // deadline task
        else if (input.startsWith("deadline"))
            newTaskList.deadlineTask(input, inputSplit);

            // event task
        else if (input.startsWith("event"))
            newTaskList.eventTask(input, inputSplit);

            // delete task
        else if (input.startsWith("delete"))
            newTaskList.deleteTask(input, inputSplit);

            // view scheduled task for the date
        else if (input.startsWith("schedule for"))
            newTaskList.scheduleTask(input, inputSplit);

        else if (input.startsWith("find"))
            newTaskList.findTask(input, inputSplit);

        else if (input.startsWith("fixed"))
            newTaskList.fixedDurationTasks(input,inputSplit);

            // prompt user to enter valid input
        else
            UI.printStandardError();
    }
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
        String[] fixedItemSplit = (input.substring(6)).split(" /needs ");
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

    public static String LoadDateToString(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        LocalDate d = LocalDate.parse(s, formatter);
        return d.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));}

}