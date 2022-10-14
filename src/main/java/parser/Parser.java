package parser;

import command.*;

public class Parser {
    public static Command parse(String inputCommand) {
        String[] lineList = inputCommand.split(" ");
        String firstWord = lineList[0];
        switch (firstWord) {
            case "todo":
                String toDoTask = inputCommand.split("todo ")[1];
                return new ToDoCommand(toDoTask);
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand(true);
            case "mark":
                return new MarkCommand(Integer.parseInt(lineList[1]) - 1);
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(lineList[1]) - 1);
            case "event":
                String[] atSplitList = inputCommand.split("/at ");
                String eventDate = atSplitList[1];
                String eventTask = inputCommand.substring(6, inputCommand.indexOf("/at") - 1);
                return new EventCommand(eventTask, eventDate);
            case "deadline":
                String[] bySplitList = inputCommand.split("/by ");
                String deadLine = bySplitList[1];
                String deadLineTask = inputCommand.substring(9, inputCommand.indexOf("/by") - 1);
                return new DeadlineCommand(deadLineTask, deadLine);
            case "delete":
                int deleteIndex = Integer.parseInt(lineList[1]) - 1;
                return new DeleteCommand(deleteIndex);
            default:
                return new ErrorCommand(inputCommand);
        }
    }
}
