
package parser;

import taskList.Deadline;
import taskList.Event;
import taskList.Todo;
import ui.UI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Parser {
    private static UI ui;

    Parser() {
    }

    protected static void parse(String userInput) {
    }

    public static Todo parseTodoInput(String input) {
        String todoTask = input.substring(5);
        Todo task = new Todo(todoTask);
        return task;
    }
    public static Deadline parseDeadlineInput(String input) {
        String[] deadlineItemSplit = (input.substring(9)).split(" /by ");
        String deadlineTask = deadlineItemSplit[0];
        String deadlineBy = deadlineItemSplit[1];
        Deadline task = new Deadline(deadlineTask, deadlineBy);
        return task;
    }
    public static Event parseEventInput(String input){
        String[] eventItemSplit = (input.substring(6)).split(" /at ");
        String eventTask = eventItemSplit[0];
        String eventAt = eventItemSplit[1];
        Event task = new Event(eventTask, eventAt);
        return task;
    }

    public static LocalDate StringToDate(String s){
//        LocalDate date = LocalDate.parse(s);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(s, formatter);
        return date;
    }

    public static String DateToString (LocalDate d){
        String string = d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return string;
    }
}
//    public static Deadline parseDeadlineInput(String input, String[] inputSplit){
//        try {
//            if (inputSplit[0].length() == 4) {
//                String[] deadlineItemSplit = (input.substring(9)).split(" /by ");
//                String deadlineTask = deadlineItemSplit[0];
//                String deadlineBy = deadlineItemSplit[1];
//                Deadline newDeadlineTask = new Deadline(deadlineTask, deadlineBy);
//            return newDeadlineTask;
//        } else
//            throw new ArrayIndexOutOfBoundsException(TASK_NUMBER_OOB);
//    }
//        catch (NullPointerException e){
//        printError("NULLPOINTER");
//        throw new NullPointerException("NULL POINTER");
//    }

//
//    }
//
//
//}

//import commands.*;
//
//public class Parser {
//    public static parse(String fullCommand) {
//        return;
//    }
//
//    public static Command parseCommand(String userInput){
//        String task = userInput.split(" ")[0];
//
//        if (task.equals(ExitCommand.COMMAND_WORD) )
//            return new ExitCommand();
//
//        else if (task.equals(ListCommand.COMMAND_WORD))
//            return new ListCommand();
//
//        else if (task.equals(TodoCommand.COMMAND_WORD))
//            return new TodoCommand(userInput.substring(5));
//
//        else if (task.equals(DeadlineCommand.COMMAND_WORD))
//            return new DeadlineCommand(userInput.substring(9));
//
//        else if (task.equals(EventCommand.COMMAND_WORD))
//            return new EventCommand(userInput.substring(6));
//
//        else
//            return new ListCommand();
//    }
