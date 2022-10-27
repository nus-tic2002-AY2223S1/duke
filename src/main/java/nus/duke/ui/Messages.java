package nus.duke.ui;

/**
 * Contains all messages for displaying.
 */
public class Messages {
    public static final String MESSAGE_GOODBYE = "Bye. Remember!\n"
            + "In times of crisis, the wise build bridges while the foolish build barriers.\n";
    public static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String MESSAGE_WELCOME =
              "Hello from\n" + LOGO
            + "Wakanda forever! I'm Winston Duke\n"
            + "What can I do for you?\n";
    public static final String MESSAGE_EMPTY_INPUT = "Empty input detected, please re-enter\n";
    public static final String MESSAGE_INDEX_NOT_NUMBER = "Index must be numbers!";
    public static final String MESSAGE_NO_TASK_DESCRIPTION = "Task description cannot be empty!\n";
    public static final String MESSAGE_NO_TASK_IN_LIST = "There is 0 tasks in the list. Please use keyword: todo, deadlines or events to add task if you wish to.\n";
    public static final String MESSAGE_NO_KEYWORD_FOR_EVENT_DEADLINE = "Please use keyword \"/\" to represent date and time. E.g. /at 6 Aug 2022 1600\n";
    public static final String MESSAGE_WRONG_TIME_FORMAT = "Please key in correct time format. E.g. 1600.\n";
    public static final String MESSAGE_TASK_NUMBER_OUT_OF_RANGE = "Task number out of list range!\n";
    public static final String MESSAGE_NOT_A_TASK = "OOPS!!! I'm sorry, but I don't know what that means :-( \n"
                                                    + "Please use keywords: bye, list, mark, unmark, delete, todo, deadlines or events.\n";
    public static final String MESSAGE_NO_TIME_FOR_DEADLINE_EVENT = "Invalid command! Date and time for deadline or event cannot be empty.\n";
    public static final String MESSAGE_NOT_TIMESLOT_FOR_EVENT = "Please enter a timeslot for the event! Or use deadline instead. \n";
    public static final String MESSAGE_NO_END_DATETIME = "Please enter an end date or time for the event, or use deadline instead. \n";
    public static final String MESSAGE_DUPLICATE_TASK = "Task exist!\n";
    public static final String MESSAGE_WRONG_EVENT_DATE_TIME_FORMAT = "Please use the below format for event slot:\n"
                                                                    + Ui.getLinePrefix() + "1. DD/MM/YYYY hhmm-hhmm\n"
                                                                    + Ui.getLinePrefix() + "2. DD/MM/YYYY hhmm-DD/MM/YYYY hhmm\n"
                                                                    + Ui.getLinePrefix() + "3. DD/MM/YYYY-DD/MM/YYYY\n";
    public static final String MESSAGE_WRONG_DEADLINE_DATE_TIME_FORMAT = "Please use the below format for deadline:\n"
            + Ui.getLinePrefix() + "1. DD/MM/YYYY\n"
            + Ui.getLinePrefix() + "2. DD/MM/YYYY hhmm\n";
}
