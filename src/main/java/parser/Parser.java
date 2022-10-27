package parser;

import command.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * This method helps to extract the task description to add todo task to list.
     *
     * @param inputCommand User's command
     * @return ToDoCommand
     */
    public static Command toDoCommandParse(String inputCommand) {
        String toDoTask = inputCommand.split("todo ")[1];
        return new ToDoCommand(toDoTask);
    }

    /**
     * This method helps to extract the task index to mark the specific task done in the task list.
     *
     * @param lineList User's command list
     * @return MarkCommand
     */
    public static Command markCommandParse(String[] lineList) {
        assert (lineList[1].matches("-?\\d+(\\.\\d+)?"));
        return new MarkCommand(Integer.parseInt(lineList[1]) - 1);
    }

    /**
     * This method helps to extract the task index to mark the specific task as not done in the task list.
     *
     * @param lineList User's command list
     * @return unMarkCommand
     */
    public static Command unMarkCommandParse(String[] lineList) {
        assert (lineList[1].matches("-?\\d+(\\.\\d+)?"));
        return new UnmarkCommand(Integer.parseInt(lineList[1]) - 1);
    }

    /**
     * This method helps to extract the task description and task date and time from user's input command to add an event task to list.
     * The input date and time format determines whether to return the EventCommand object or an errorCommand object
     *
     * @param inputCommand User's command
     * @return EventCommand or ErrorCommand
     */
    public static Command eventCommandParse(String inputCommand) {
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
    }

    /**
     * This method helps to extract the task description and task deadline from user's input command to add a deadline task to list.
     * The input date and time format determines whether to return the DeadlineCommand object or an ErrorCommand object
     *
     * @param inputCommand User's command
     * @return DeadlineCommand or ErrorCommand
     */
    public static Command deadlineCommandParse(String inputCommand) {
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
    }

    /**
     * This method helps to extract the task index to delete from the task list.
     *
     * @param lineList User's command list
     * @return DeleteCommand
     */
    public static Command deleteCommandParse(String[] lineList) {
        assert (lineList[1].matches("-?\\d+(\\.\\d+)?"));
        int deleteIndex = Integer.parseInt(lineList[1]) - 1;
        return new DeleteCommand(deleteIndex);
    }

    /**$
     * This method helps to extract the task date and time which user used to search through all deadlines and events.
     * The input date and time format determines whether to return the FindTaskCommand object or an ErrorCommand object
     *
     * @param inputCommand User's command
     * @return FindTaskCommand or ErrorCommand
     */
    public static Command findTaskCommandParse(String inputCommand) {
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
    }

    /**
     * This method helps to extract the input task index and priority level which user want to set for a certain task.
     *
     * @param inputCommand User's command
     * @return PriorityCommand
     */
    public static Command priorityCommandParse(String inputCommand) {
        String input = inputCommand.split("priority ")[1];
        String[] inputList = input.split(" ");
        assert (inputList[0].matches("-?\\d+(\\.\\d+)?"));
        int taskIndex = Integer.parseInt(inputList[0]) - 1;
        String priority = inputList[1];
        return new PriorityCommand(priority, taskIndex);
    }

    /**
     * This method helps to extract the input keyword which user want to use it to search among all the task description.
     *
     * @param inputCommand User's command
     * @return FindCommand
     */
    public static Command findCommandParse(String inputCommand) {
        String keyword = inputCommand.split("find ")[1];
        return new FindCommand(keyword);
    }

    /**
     * This method helps to extract the task description, start and end date of the period from user's input command to add a doWithinPeriod task to list.
     * The input date format determines whether to return the DoWithinPeriodCommand object or an ErrorCommand object
     *
     * @param inputCommand User's command
     * @return DoWithinPeriodCommand or ErrorCommand
     */
    public static Command doWithinPeriodCommandParse(String inputCommand) {
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
    }


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
                return toDoCommandParse(inputCommand);
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand(true);
            case "mark":
                return markCommandParse(lineList);
            case "unmark":
                return unMarkCommandParse(lineList);
            case "event":
                return eventCommandParse(inputCommand);
            case "deadline":
                return deadlineCommandParse(inputCommand);
            case "delete":
                return deleteCommandParse(lineList);
            case "findtask":
                return findTaskCommandParse(inputCommand);
            case "priority":
                return priorityCommandParse(inputCommand);
            case "find":
                return findCommandParse(inputCommand);
            case "dowithinperiod":
                return doWithinPeriodCommandParse(inputCommand);
            default:
                return new ErrorCommand(inputCommand);
        }
    }
}
