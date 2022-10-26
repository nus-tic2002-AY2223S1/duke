package parser;

import command.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * This method helps to understand what user's command is trying to do.
     *
     * @param inputCommand User's command
     * @return Different types of command (E.g. ToDoCommand, ListCommand, etc. )
     */
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
                assert (lineList[1].matches("-?\\d+(\\.\\d+)?"));
                return new MarkCommand(Integer.parseInt(lineList[1]) - 1);
            case "unmark":
                assert (lineList[1].matches("-?\\d+(\\.\\d+)?"));
                return new UnmarkCommand(Integer.parseInt(lineList[1]) - 1);
            case "event":
                String eventTask = inputCommand.substring(6, inputCommand.indexOf("/at") - 1);
                String eventDate = inputCommand.split("/at ")[1];
                DateTimeFormatter eventDateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                String eventDatetimeString;
                try {
                    LocalDateTime eventDatetime = LocalDateTime.parse(eventDate, eventDateFormat);
                    eventDatetimeString = eventDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
                    return new EventCommand(eventTask, eventDatetimeString);
                } catch (DateTimeParseException e) {
                    return new ErrorCommand(inputCommand + "\n" + " ☹ OOPS!!! event has the wrong datetime format. " + "Event date format should be {d/MM/yyyy HHmm}");
                }
            case "deadline":
                String deadLineTask = inputCommand.substring(9, inputCommand.indexOf("/by") - 1);
                String deadline = inputCommand.split("/by ")[1];
                DateTimeFormatter deadlineFormat = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                String deadlineString;
                try {
                    LocalDateTime deadlineDatetime = LocalDateTime.parse(deadline, deadlineFormat);
                    deadlineString = deadlineDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
                    return new DeadlineCommand(deadLineTask, deadlineString);
                } catch (DateTimeParseException e) {
                    return new ErrorCommand(inputCommand + "\n" + " ☹ OOPS!!! deadline has the wrong datetime format. " + "Deadline format should be {d/MM/yyyy HHmm}");
                }
            case "delete":
                assert (lineList[1].matches("-?\\d+(\\.\\d+)?"));
                int deleteIndex = Integer.parseInt(lineList[1]) - 1;
                return new DeleteCommand(deleteIndex);

            case "findtask":
                String taskDate = inputCommand.split("findtask ")[1];
                DateTimeFormatter taskDateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                String taskDatetimeString;
                try {
                    LocalDateTime taskDatetime = LocalDateTime.parse(taskDate, taskDateFormat);
                    taskDatetimeString = taskDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
                    return new FindtaskCommand(taskDatetimeString);
                } catch (DateTimeParseException e) {
                    return new ErrorCommand(inputCommand + "\n" + " ☹ OOPS!!! event has the wrong datetime format. " + "Event date format should be {d/MM/yyyy HHmm}");
                }

            case "priority":
                String input = inputCommand.split("priority ")[1];
                String[] inputList = input.split(" ");
                assert (inputList[0].matches("-?\\d+(\\.\\d+)?"));
                int taskIndex = Integer.parseInt(inputList[0]) - 1;
                String priority = inputList[1];
                return new PriorityCommand(priority, taskIndex);

            case "find":
                String keyword = inputCommand.split("find ")[1];
                return new FindCommand(keyword);

            case "dowithinperiod":
                String doWithinPeriodTask = inputCommand.substring(15, inputCommand.indexOf("/between") - 1);
                String startPeriodDateInput = (inputCommand.split("/between ")[1]).split(" /and")[0];
                String endPeriodDateInput = inputCommand.split("/and ")[1];

                String startPeriodDateString;
                String endPeriodDateString;
                DateTimeFormatter periodDateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");

                try {
                    LocalDate startPeriodDate = LocalDate.parse(startPeriodDateInput, periodDateFormat);
                    LocalDate endPeriodDate = LocalDate.parse(endPeriodDateInput, periodDateFormat);

                    if (endPeriodDate.isAfter(startPeriodDate)) {
                        startPeriodDateString = startPeriodDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
                        endPeriodDateString = endPeriodDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
                        return new DoWithinPeriodCommand(doWithinPeriodTask, startPeriodDateString, endPeriodDateString);
                    } else {
                        return new ErrorCommand(inputCommand + "\n" + " ☹ OOPS!!! " + "The end period date should be after start period date");
                    }
                } catch (DateTimeParseException e) {
                    return new ErrorCommand(inputCommand + "\n" + " ☹ OOPS!!! task has the wrong date format. " + "DoWithinPeriod date format should be {d/MM/yyyy}");
                }

            default:
                return new ErrorCommand(inputCommand);
        }
    }
}
