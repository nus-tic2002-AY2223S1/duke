package Duke.Parser;
import Duke.Exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {
    public static String command;
    public static String listIdx;
    private String todoDesc;
    private String deadlineDesc;
    private String eventDesc;
    private LocalDate deadlineDate;
    private LocalDate eventDate;
    private LocalTime eventStart;
    private LocalTime eventEnd;

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

    private void getTodo(String detail) throws DukeException {
        todoDesc = detail;
    }

    private void getDeadline(String detail) throws DukeException {
        String detailSplit[] = detail.split(" /by ", 2);
        deadlineDesc = detailSplit[0];
        String date = detailSplit[1];

        if(date.isEmpty()) {
            throw new DukeException("Sorry. Please enter date and time for deadline.");
        }
        try {
            deadlineDate = LocalDate.parse(date);
            System.out.println("parsed date " + deadlineDate);
        } catch(DateTimeParseException e) {
            throw new DukeException("Sorry. Date format needs to be YYYY-MM-DD.");
        }
    }

    private void getEvent(String detail) throws DukeException {
        String detailSplit[] = detail.split(" /at ", 2);
        String dateTime[] = detailSplit[1].split(" ", 2);
        System.out.println("detail split " + detailSplit[0]);
        System.out.println("date." + dateTime[0]);
        System.out.println("time." + dateTime[1]);
        try {
            eventDate = LocalDate.parse(dateTime[0]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry. Date format needs to be yyyy-MM-dd.");
        }

        String[] start = dateTime[1].split(" - ", 2);
        System.out.println("start." + start[0]);
        System.out.println("end." + start[1]);
        if (start.length < 2) {
            throw new DukeException("Sorry. Time format needs to be 12:00 - 13:00");
        }
        try {
            eventStart = LocalTime.parse(dateTime[0]);
            eventEnd = LocalTime.parse(dateTime[1]);
            if (eventStart.compareTo(eventEnd) > 0) {
                throw new DukeException("Sorry. Start time cannot be later than end time.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry. Time format needs to be HH-mm.");
        }
    }

    public String getCommand() {
        return command;
    }

    public String getListIdx() {
        return listIdx;
    }

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