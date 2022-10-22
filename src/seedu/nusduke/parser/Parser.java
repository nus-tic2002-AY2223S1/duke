package seedu.nusduke.parser;


import seedu.nusduke.data.DukeException;
import seedu.nusduke.ui.Messages;

public class Parser {

    public static Command parse (String command) throws DukeException {
        if (command.isEmpty()){
            throw new DukeException(Messages.MESSAGE_EMPTY_INPUT);
        }
        String[] split = command.split(" ",2);
        String[] description;
        Command userInput;
        switch (split[0]){
            case "bye":
                userInput = new Command(true);
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
            case "event":
                try {
                    checkDescription(split);
                    description = splitDescription(split[1]);
                    checkDateAndTime(split[0], description);
                } catch (DukeException e){
                    throw new DukeException(e.getMessage());
                }
                userInput = new Command(split[0], description[0], description[1]);
                break;
            default:
                throw new DukeException(Messages.MESSAGE_NOT_A_TASK);
        }

        return userInput;
    }

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

    public static void checkDescription(String[] description) throws DukeException{
        if (description.length < 2) {
                throw new DukeException(Messages.MESSAGE_NO_TASK_DESCRIPTION);
        }
    }

    public static String[] splitDescription(String description) throws DukeException{

        if (!description.contains("/")){
            throw new DukeException(Messages.MESSAGE_NO_KEYWORD_FOR_EVENT_DEADLINE);
        } else {
            String[] split = description.split("/", 2);
            return split;
        }
    }

    public static void checkDateAndTime(String command, String[] description) throws DukeException{
        if (description.length < 2){
            throw new DukeException(Messages.MESSAGE_NO_TIME_FOR_DEADLINE_EVENT);
        }
        if (command.equals("event")){
            try{
                checkTimeSlot(description[1]);
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    public static void checkTimeSlot(String dateAndTime) throws DukeException {
        if (dateAndTime.contains("-")) {
        } else {
            throw new DukeException(Messages.MESSAGE_NOT_TIMESLOT_FOR_EVENT);
        }
    }


}
