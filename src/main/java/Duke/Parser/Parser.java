package Duke.Parser;
import Duke.Exception.DukeException;
import Duke.TaskList.Task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    public static String command;
    public static String listIdx;
    private String taskDesc;
    private String todoDesc;
    private String deadlineDesc;
    private String eventDesc;
    private LocalDate deadlineDate;
    private LocalDate eventDate;
    private LocalTime eventStart;
    private LocalTime eventEnd;

    /**
     * Parser method takes in the user input and runs specific methods according to which command the user has entered
     * @param userInput is the input the user has entered
     * @throws DukeException
     */
    public Parser(String userInput) throws DukeException {
        boolean oneWord = false;
        String[] userInputArr = userInput.split(" ",2);
        command = userInputArr[0].toLowerCase();
        if(command.equals("list") || command.equals("bye")) {
            oneWord = true;
        }
        switch(command.toLowerCase()) {
            case "mark":

            case "unmark":

            case "find":
                if(userInput.length() < 2) {
                    throw new DukeException("Sorry, you need to provide a keyword to search.");
                }
                taskDesc = userInputArr[1];
                break;
            case "delete":
                listIdx = userInputArr[1];
                break;
            case "todo":
                getTodo(userInputArr[1]);
                break;
            case "deadline":
                getDeadline(userInputArr[1]);
                break;
            case "event":
                getEvent(userInputArr[1]);
                break;
            default:
                if(!oneWord) {
                    throw new DukeException("Sorry. I do not understand this command.");
                }
        }
    }

    /**
     * getTodo method is to get the description of Todo
     * @param detail
     * @throws DukeException
     */
    private void getTodo(String detail) throws DukeException {
        todoDesc = detail;
    }

    /**
     * getDeadline method is to get the description of Deadline
     * @param detail
     * @throws DukeException
     */
    private void getDeadline(String detail) throws DukeException {
        String detailSplit[] = detail.split(" /by ", 2);
        deadlineDesc = detailSplit[0];
        String date = detailSplit[1];

        if(date.isEmpty()) {
            throw new DukeException("Sorry. Please enter date and time for deadline.");
        }
        try {
            deadlineDate = LocalDate.parse(date);
        } catch(DateTimeParseException e) {
            throw new DukeException("Sorry. Date format needs to be YYYY-MM-DD.");
        }
    }

    /**
     * getEvent method is to get the description of Event
     * @param detail
     * @throws DukeException
     */
    private void getEvent(String detail) throws DukeException {
        String detailSplit[] = detail.split(" /at ", 2);
        String dateTime[] = detailSplit[1].split(" ", 2);
        eventDesc = detailSplit[0];
        try {
            eventDate = LocalDate.parse(dateTime[0]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry. Date format needs to be yyyy-MM-dd.");
        }

        String[] start = dateTime[1].split(" - ", 2);
        if (start.length < 2) {
            throw new DukeException("Sorry. Time format needs to be for example 10:00 - 13:00");
        }
        try {
            eventStart = LocalTime.parse(start[0]);
            eventEnd = LocalTime.parse(start[1]);
            if (eventStart.compareTo(eventEnd) > 0) {
                throw new DukeException("Sorry. Start time cannot be later than end time.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry. Time format needs to be HH:mm.");
        }
    }

    public String getCommand() {
        return command;
    }

    public String getListIdx() {
        return listIdx;
    }

    public String getTaskDesc() { return taskDesc; }

    public String getTodoDesc() {
        return todoDesc;
    }

    public String getDeadlineDesc() {
        return deadlineDesc;
    }

    public LocalDate getDatelineDate() {
        return deadlineDate;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public LocalTime getEventStart() {
        return eventStart;
    }

    public LocalTime getEventEnd() {
        return eventEnd;
    }
}