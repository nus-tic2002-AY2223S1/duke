package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.*;
import exceptions.DukeException;


public class Parser {
    public static Command parse(String command) {
        String task;
        int index;
        String date;
        String[] inputArray = command.split(" ");
        switch (inputArray[0]) {
            case "todo":
                task = command.split("todo ")[1];
                return new TodoCommand(task);

            case "event":
                index = command.indexOf(" /at ");
                task = command.substring(6, index);
                date = command.split(" /at ")[1];
                return new EventCommand(task, dateFormat(date));

            case "deadline":
                index = command.indexOf(" /by ");
                task = command.substring(9, index);
                date = command.split(" /by ")[1];
                dateFormat(date);
                return new DeadlineCommand(task, dateFormat(date));

            case "list":
                return new ListCommand();

            case "bye":
                return new ByeCommand();
            case "mark":
                return new MarkCommand(inputArray[1]);
            case "unmark":
                return new UnmarkCommand(inputArray[1]);

            case "delete":
                return new DeleteCommand(inputArray[1]);
            case"nextdue":
                return new NextDueCommand();
            case"find":
                return new FindCommand(inputArray[1]);
            default:

                return new ErrorCommand();

        }


    }
    /**
    *Pass in date time of format ("dd/MM/yyyy HHmm"), output as ("MMM dd yyyy hh:mm a")
    */
    public static String dateFormat(String date) {
        DateTimeFormatter givenDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime givenDate = LocalDateTime.parse(date, givenDateFormat);
        return givenDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));

    }


}
