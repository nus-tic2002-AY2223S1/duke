package nus.duke.parser;


import nus.duke.data.DukeException;
import nus.duke.ui.Messages;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String DEFAULT_TIME = "23:59:59";
    public static final String DEFAULT_START_TIME = "00:00:00";
    /**
     * Take in the user command and make sense of it to duke.
     *
     * @return An executable command.
     * @throws DukeException
     * when the user input is empty or
     * when the user input is out of duke scope.
     */
    public static Command parse (String command) throws DukeException {
        Command userInput;
        ArrayList<String> description;
        if (command.isEmpty()){
            throw new DukeException(Messages.MESSAGE_EMPTY_INPUT);
        }
        if (command.contains("after")){
            userInput = addDoAfter(command);
            return userInput;
        }
        String[] split = command.split(" ",2);
        switch (split[0]){
            case "bye":
                userInput = new Command(split[0], true);
                break;
            case "list":
                userInput = new Command(split[0]);
                break;
            case "find":
                userInput = new Command(split[0], split[1]);
                break;
            case "mark":
            case "unmark":
            case "delete":
                userInput = addCommandWithIndex(split);
                break;
            case "todo":
                userInput = addTodo(split);
                break;
            case "deadline":
                userInput = addDeadline(split);
                break;
            case "event":
                userInput = addEvent(split);
                break;
            default:
                throw new DukeException(Messages.MESSAGE_NOT_A_TASK);
        }
        return userInput;
    }

    /**
     * Take in a DoAfter type user command and create a Command class object.
     *
     * @param command Command that input by the user.
     * @return User command.
     * @throws DukeException
     * when the DoAfter command format is not correct.
     */
    public static Command addDoAfter(String command) throws DukeException{
        Command userInput;
        ArrayList<String> description;
        String[] split = command.split("after", 2);
        if (split.length != 2) {
            throw new DukeException(Messages.MESSAGE_DOAFTER_FORMAT);
        }
        String reformedCommand = split[0] + "/after" + split[1];
        description = splitDescription(reformedCommand);
        assert description.size() <= 4 : "user command for DoAfter should only be split to arraylist of size < 5." ;
        if (description.size() < 3){
            throw new DukeException(Messages.MESSAGE_DOAFTER_FORMAT);
        }
        int dayOfWeek = dayOfWeek(description.get(2));
        assert dayOfWeek <= 7 && dayOfWeek >=0 : "value of dayOfWeek should be in between 0-7";
        if (description.size() <= 4 && (isDate(description.get(2)) || dayOfWeek != 0)) {
            if (description.size() == 3) {
                if (dayOfWeek !=0) {
                    description.set(2, formatNaturalDate(description.get(2)));
                }
                userInput = new Command("DoAfter", description.get(0), description.get(2).replace("/", "-"), DEFAULT_TIME);
            } else {
                try {
                    description.set(3, formatTime(description.get(3)));
                } catch (DukeException e) {
                    throw e;
                }
                userInput = new Command("DoAfter", description.get(0), description.get(2).replace("/", "-"), description.get(3));
            }
        } else {
            userInput = new Command("DoAfter", split[0].trim(), split[1].trim());
        }
        return userInput;
    }

    /**
     * Take in a command type user command that requires an index and create a Command object.
     *
     * @param split Split the command that input by the user in a "command type" + "index" format.
     * @return User command as a Command object.
     * @throws DukeException
     * when the index input with command "mark", "unmark" and "delete" cannot be parsed to an integer.
     */
    public static Command addCommandWithIndex(String[] split) throws DukeException{
        Command userInput;
        if (isNumeric(split[1])) {
            int index = Integer.parseInt(split[1]) - 1;
            userInput = new Command(split[0], index);
        } else {
            throw new DukeException(Messages.MESSAGE_INDEX_NOT_NUMBER);
        }
        return userInput;
    }

    /**
     * Take in a Todo type user command and create a Command object.
     *
     * @param split Split the command that input by the user in a "command type" + "command body" format.
     * @return User command as a Command object.
     */
    public static Command addTodo(String[] split) throws DukeException{
        Command userInput;
        try {
            checkDescription(split);
        } catch (DukeException e){
            throw new DukeException(e.getMessage());
        }
        userInput = new Command(split[0], split[1]);
        return userInput;
    }

    /**
     * Take in a Deadline type user command and create a Command object.
     *
     * @param split Split the command that input by the user in a "command type" + "command body" format.
     * @return User command as a Command object.
     * @throws DukeException
     * when wrong date and time format is used when adding "deadline".
     */
    public static Command addDeadline(String[] split) throws DukeException{
        Command userInput;
        ArrayList<String> description;
        try {
            checkDescription(split);
            description = splitDescription(split[1]);
            assert description.size() <= 4 : "user command for Deadline should only be split to arraylist of size < 5." ;
            description = checkDateAndTime(split[0], description);
        } catch (DukeException e){
            throw new DukeException(e.getMessage());
        }
        if (description.size() == 3) {
            userInput = new Command(split[0], description.get(0), description.get(1), description.get(2), DEFAULT_TIME);
        } else if (description.size() ==4) {
            userInput = new Command(split[0], description.get(0), description.get(1), description.get(2), description.get(3));
        } else {
            throw new DukeException(Messages.MESSAGE_WRONG_DEADLINE_DATE_TIME_FORMAT);
        }
        return userInput;
    }

    /**
     * Take in an Event type user command and create a Command object.
     *
     * @param split Split the command that input by the user in a "command type" + "command body" format.
     * @return User command as a Command object.
     */
    public static Command addEvent(String[] split) throws DukeException{
        Command userInput;
        ArrayList<String> description;
        try {
            checkDescription(split);
            description = splitDescription(split[1]);
            assert description.size() <= 6 : "user command for Event should only be split to an arraylist of size < 7." ;
            description = checkDateAndTime(split[0], description);
        } catch (DukeException e){
            throw new DukeException(e.getMessage());
        }
        if (description.size() == 4) {
            userInput = new Command(split[0], description.get(0), description.get(1),
                    description.get(2), DEFAULT_START_TIME, description.get(3), DEFAULT_TIME);
        }
        else if (description.size() == 5) {
            userInput = new Command(split[0], description.get(0), description.get(1),
                    description.get(2), description.get(3), description.get(2), description.get(4));
        }
        else {
            userInput = new Command(split[0], description.get(0), description.get(1),
                    description.get(2), description.get(3), description.get(4), description.get(5));
        }
        return userInput;
    }
    /**
     * Take in a String to check it can be parsed to double format.
     *
     * @param strNum String to be checked.
     * @return true or false.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * To check if the user input has a description while adding a task.
     *
     * @throws DukeException when the user input for an adding command comes without description.
     */
    public static void checkDescription(String[] description) throws DukeException{
        if (description.length < 2) {
                throw new DukeException(Messages.MESSAGE_NO_TASK_DESCRIPTION);
        }
    }
    /**
     * Further split the description into task description and date/time for "deadline" and "event".
     *
     * @throws DukeException when there is no possible date/time detected.
     * @return An ArrayList of String.
     */
    public static ArrayList<String> splitDescription(String description) throws DukeException{

        if (!description.contains("/")){
            throw new DukeException(Messages.MESSAGE_NO_KEYWORD_FOR_EVENT_DEADLINE);
        } else {
            if (isNumeric(String.valueOf(description.charAt(description.indexOf("/") - 1)))){
                throw new DukeException(Messages.MESSAGE_NO_KEYWORD_FOR_EVENT_DEADLINE);
            }
            String[] split = description.split("/", 2);
            ArrayList<String> splitFurther = new ArrayList<String> (Arrays.asList(split[1].split(" ")));
            splitFurther.add(0 , split[0].trim());
            return splitFurther;
        }
    }

    /**
     * Conduct further check on the date/time to ensure that the format is correct depends on its command type.
     *
     * @return An ArrayList of the fully split and formatted description of the command.
     * @throws DukeException
     * when there is no date/time detected or
     * when the time/date given for event is not a timeslot.
     */
    public static ArrayList<String> checkDateAndTime(String command, ArrayList<String> description) throws DukeException{
        if (description.size() < 3){
            throw new DukeException(Messages.MESSAGE_NO_TIME_FOR_DEADLINE_EVENT);
        }

        if (command.equals("deadline")) {
            description = FormatDateAndTimeForDeadline(description);
        } else if (command.equals("event")){
            try{
                checkTimeSlot(description);
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
            description = FormatDateAndTimeForEvent(description);
        }
        return description;
    }
    /**
     * Format the date/time for deadline command.
     *
     * @return An ArrayList of the fully split and formatted description for deadline.
     */
    public static ArrayList<String> FormatDateAndTimeForDeadline(ArrayList<String> description) throws DukeException {
        int dayOfWeek = dayOfWeek(description.get(2));
        assert dayOfWeek <= 7 && dayOfWeek >=0 : "value of dayOfWeek should be in between 0-7";
        if (dayOfWeek != 0){
            description.set(2, formatNaturalDate(description.get(2)));
        }
        description.set(2,description.get(2).replace("/", "-"));
        if (description.size() == 4) {
            description.set(3, formatTime(description.get(3)));
        }
        return description;
    }

    /**
     * Format the date/time for event command.
     *
     * @return An ArrayList of the fully split and formatted description for event.
     * @throws DukeException for wrong Event date/time format.
     */
    public static ArrayList<String> FormatDateAndTimeForEvent(ArrayList<String> description) throws DukeException {
        String[] split;
        switch (description.size()){
            case 3:
                split = description.get(2).split("-", 2);
                description.set(2, split[0].replace("/", "-"));
                description.add(split[1].replace("/", "-"));
                break;
            case 4:
                split = description.get(3).split("-", 2);
                int dayOfWeek = dayOfWeek(description.get(2));
                assert dayOfWeek <= 7 && dayOfWeek >=0 : "value of dayOfWeek should be in between 0-7";
                if (dayOfWeek != 0){
                    description.set(2, formatNaturalDate(description.get(2)));
                }
                description.set(2, description.get(2).replace("/", "-"));
                description.set(3, formatTime(split[0]));
                description.add(formatTime(split[1]));
                break;
            case 5:
                split = description.get(3).split("-", 2);
                description.set(2, description.get(2).replace("/", "-"));
                description.set(3, formatTime(split[0]));
                description.add(4, split[1].replace("/", "-"));
                description.set(5, formatTime(description.get(5)));
                break;
            default:
                throw new DukeException(Messages.MESSAGE_WRONG_EVENT_DATE_TIME_FORMAT);
        }
        return description;
    }
    /**
     * Check that date/time provided for "event" command is a period of time containing "-" between two date/time provided.
     *
     * @throws DukeException when the "-" is the last Char of that String, which means no end time.
     */
    public static void checkTimeSlot(ArrayList<String> description) throws DukeException {
        boolean containDash = false;
        for (int i = 0; i < description.size(); i++){
            if (description.get(i).contains("-")){
                containDash = true;
                if (i == description.size() - 1) {
                    if (description.get(i).indexOf("-") == description.get(i).length() - 1) {
                        throw new DukeException(Messages.MESSAGE_NO_END_DATETIME);
                    }
                }
            }
        }
        if (containDash == false) {
            throw new DukeException(Messages.MESSAGE_NOT_TIMESLOT_FOR_EVENT);
        }
    }
    /**
     * Format the time in a HH:mm:ss format.
     *
     * @return A string of time in HH:mm:ss.
     */
    public static String formatTime(String timeString) throws DukeException {
        int time;
        int hour;
        int minute;
        try {
            time = Integer.parseInt(timeString);
        } catch (NumberFormatException e) {
            throw new DukeException(Messages.MESSAGE_WRONG_TIME_FORMAT);
        }
        hour = time / 100;
        minute = (time - hour * 100) % 60;
        timeString = String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + "00";
        return timeString;
    }

    /**
     * Format the string in day of week format, e.g. mon, monday etc. to a date sting in the format
     * of "dd/MM/yyyy".
     *
     * @return A string of date in dd/MM/yyyy format.
     */
    public static String formatNaturalDate(String naturalDate) {
        int dayOfWeek = dayOfWeek(naturalDate);
        LocalDate current = LocalDate.now();
        current = current.with(TemporalAdjusters.next(DayOfWeek.of(dayOfWeek)));
        String nextDate = current.format(DATE_FORMATTER);
        return nextDate;
    }
    /**
     * Take in a string value and check if it is a day of week.
     *
     * @return An integer value from 1 to 7, which represents Monday, Tuesday ... Sunday respectively.
     */
    public static int dayOfWeek(String naturalDate) {
        naturalDate = naturalDate.toUpperCase();
        int dayOfWeek = 0;
        switch (naturalDate) {
            case "MON":
            case "MONDAY":
                dayOfWeek = 1;
                break;
            case "TUE":
            case "TUESDAY":
                dayOfWeek = 2;
                break;
            case "WED":
            case "WEDNESDAY":
                dayOfWeek = 3;
                break;
            case "THUR":
            case "THURSDAY":
                dayOfWeek = 4;
                break;
            case "FRI":
            case "FRIDAY":
                dayOfWeek = 5;
                break;
            case "SAT":
            case "SATURDAY":
                dayOfWeek = 6;
                break;
            case "SUN":
            case "SUNDAY":
                dayOfWeek = 7;
                break;
        }
        return dayOfWeek;
    }
    /**
     * Check if a given string is a date in "dd/MM/yyyy" format.
     *
     * @return true if yes, false if is not.
     */
    public static boolean isDate(String dateString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
