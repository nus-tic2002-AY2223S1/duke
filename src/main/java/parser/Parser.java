
package parser;

import tasklist.*;
import ui.ErrorMessages;
import ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Parser {

    public static void parse(String input, TaskList newTaskList){
        try {
            String[] inputSplit = input.split(" ");

            // enter bye to end chat
            if (input.equals("bye"))
                newTaskList.exit();

                // to list all items
            else if (input.equals("list"))
                newTaskList.listTask(inputSplit, input);

                // mark items
            else if (input.startsWith("done"))
                newTaskList.markTask(inputSplit, input);

                // unmarked items
            else if (input.startsWith("undone"))
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
            else if (input.startsWith("schedule"))
                newTaskList.scheduleTask(input, inputSplit);

            else if (input.startsWith("find"))
                newTaskList.findTask(input, inputSplit);

            else if (input.startsWith("fixed"))
                newTaskList.fixedDurationTasks(input, inputSplit);

                // prompt user to enter valid input
            else
                UI.printStandardError();
        }
        catch (Exception e){
        }
    }

    // format todo input before it is added into task list
    public static Todo parseTodoInput(String input) {
        String todoTask = input.substring(5);
        return new Todo(todoTask);
    }

    // format deadline input before it is added into task list
    public static Deadline parseDeadlineInput(String input) {
        String[] deadlineItemSplit = (input.substring(9)).split(" /by ");
        String deadlineTask = deadlineItemSplit[0];
        String deadlineBy = deadlineItemSplit[1];
        return new Deadline(deadlineTask, deadlineBy);
    }

    // format event input before it is added into task list
    public static Event parseEventInput(String input){
        String[] eventItemSplit = (input.substring(6)).split(" /at ");
        String eventTask = eventItemSplit[0];
        String eventAt = eventItemSplit[1];
        return new Event(eventTask, eventAt);
    }

    // format schedule input before it is added into task list
    public static String parseScheduleInput(String input){
        String[] scheduleDateSplit = input.split(" ");
        String scheduleDate = scheduleDateSplit[1];
        return scheduleDate;
    }

    // format find input to search for input in the task list
    public static String parseFindInput(String input){
        return input.substring(5);
    }

    // format fixed duration input before it is added into task list
    public static Fixed parseFixedDurationInput (String input){
        String[] fixedItemSplit = (input.substring(6)).split(" /needs ");
        String fixedTask = fixedItemSplit [0];
        String fixedNeeds = fixedItemSplit[1];
        return new Fixed(fixedTask, fixedNeeds);
    }

    // convert input date to LocalDate format
    public static LocalDate stringToDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        return LocalDate.parse(s, formatter);
    }

    // convert LocalDate date to String format
    public static String dateToString (LocalDate d){
        return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    // convert date retrieved from loaded file to String in task format, so task can be added to task list
    public static String loadDateToString(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        LocalDate d = LocalDate.parse(s, formatter);
        return d.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));}

}