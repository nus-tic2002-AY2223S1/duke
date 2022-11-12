package parser;

import commands.*;
import exceptions.DukeException;

public class Parser {

    public static Command parse(String input) {
        String[] arrInput = input.split(" ", 2);
        String errorType;

        try {
            String firstWord = arrInput[0];
            switch (firstWord) {
                case "todo":
                    return new TodoCommand(arrInput[1]);
                case "deadline":
                    String[] dInput = arrInput[1].split(" /");
                    return new DeadlineCommand(dInput[0], dInput[1]);
                case "event":
                    String[] eInput = arrInput[1].split(" /");
                    return new EventCommand(eInput[0], eInput[1]);
                case "delete":
                    return new DeleteCommand(arrInput[1]);
                case "list":
                    return new ListCommand();
                case "mark":
                    return new MarkCommand(arrInput[1]);
                case "unmark":
                    return new UnmarkCommand(arrInput[1]);
                case "bye":
                    return new ByeCommand();
                default:
                    throw new DukeException();
            }
        } catch (DukeException e) {
            errorType = "invalid";
        } catch (ArrayIndexOutOfBoundsException e) {
            errorType = "missing";
        }
        return new ErrorCommand(errorType);
    }
}
