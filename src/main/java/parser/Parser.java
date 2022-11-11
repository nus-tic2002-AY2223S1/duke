package parser;


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
                index=command.indexOf(" /at ");
                task=command.substring(6,index);
                date=command.split(" /at ")[1];
                return new EventCommand(task,date);

            case"deadline":
                index=command.indexOf(" /by ");
                task=command.substring(9,index);
                date=command.split(" /by ")[1];
                return new DeadlineCommand(task,date);

            case"list":
                return new ListCommand();

            case"bye":
                return new ByeCommand();
            case"mark":
                return new MarkCommand(inputArray[1]);
            case "unmark":
                return new UnmarkCommand(inputArray[1]);

            case"delete":
                return new DeleteCommand(inputArray[1]);
            default:
                try {
                    throw new DukeException();

                } catch (DukeException e) {


                }


        }


        return null;
    }

}
