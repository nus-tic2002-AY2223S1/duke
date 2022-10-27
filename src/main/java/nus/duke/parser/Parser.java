package nus.duke.parser;


import nus.duke.data.DukeException;
import nus.duke.ui.Messages;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    public static final String DEFAULT_TIME = "23:59:59";
    public static final String DEFAULT_START_TIME = "00:00:00";
    /**
     * Take in the user command and make sense of it to duke.
     *
     * @return An executable command.
     * @throws DukeException
     * when the user input is empty or
     * when the index input with command "mark", "unmark" and "delete" cannot be parsed to an integer or
     * when wrong date and time format is used when adding "deadline" and "event" or
     * when the user input is out of duke scope.
     */
    public static Command parse (String command) throws DukeException {
        if (command.isEmpty()){
            throw new DukeException(Messages.MESSAGE_EMPTY_INPUT);
        }
        String[] split = command.split(" ",2);
        ArrayList<String> description;
        Command userInput;
        switch (split[0]){
            case "bye":
                userInput = new Command(split[0], true);
                break;
            case "list":
                userInput  = new Command(split[0]);
                break;
            case "mark":
            case "unmark":
            case "delete":
                if (isNumeric(split[1])) {
                    int index = Integer.parseInt(split[1]) - 1;
                    userInput = new Command(split[0], index);
                } else {
                    throw new DukeException(Messages.MESSAGE_INDEX_NOT_NUMBER);
                }
                break;
            case "todo":
                try {
                    checkDescription(split);
                } catch (DukeException e){
                    throw new DukeException(e.getMessage());
                } finally {
                    userInput = new Command(split[0], split[1]);
                }
                break;
            case "deadline":
                try {
                    checkDescription(split);
                    description = splitDescription(split[1]);
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
                break;
            case "event":
                try {
                    checkDescription(split);
                    description = splitDescription(split[1]);
                    description = checkDateAndTime(split[0], description);
                } catch (DukeException e){
                    throw new DukeException(e.getMessage());
                }
                if (description.size() == 4) {
                    userInput = new Command(split[0], description.get(0), description.get(1), description.get(2), DEFAULT_START_TIME, description.get(3), DEFAULT_TIME);
                }
                else if (description.size() == 5) {
                    userInput = new Command(split[0], description.get(0), description.get(1), description.get(2), description.get(3), description.get(2), description.get(4));
                }
                else {
                    userInput = new Command(split[0], description.get(0), description.get(1), description.get(2), description.get(3), description.get(4), description.get(5));
                }
                break;
            default:
                throw new DukeException(Messages.MESSAGE_NOT_A_TASK);
        }

        return userInput;
    }

    /**
     * Take in a String to check it can be parsed to double format.
     *
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
            String[] split = description.split("/", 2);
            ArrayList<String> splitFurther = new ArrayList<String> (Arrays.asList(split[1].split(" ")));
            splitFurther.add(0 , split[0]);
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
     * @throws DukeException for wrong date/time format.
     */
    public static ArrayList<String> FormatDateAndTimeForEvent(ArrayList<String> description) throws DukeException {
        String[] split;
        switch (description.size()){
            case 3:
                split = description.get(2).split("-", 2);
                split[0].replace("/", "-");
                split[1].replace("/", "-");
                description.set(2, split[0]);
                description.add(split[1]);
                break;
            case 4:
                split = description.get(3).split("-", 2);
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
                if (description.get(i).indexOf("-") == description.get(i).length() - 1){
                    throw new DukeException(Messages.MESSAGE_NO_END_DATETIME);
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
     */
    public static String formatTime(String timeString) throws DukeException{
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

}
