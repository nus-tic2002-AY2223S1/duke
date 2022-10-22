package nusduke.ui;

import nusduke.tasklist.Task;
import nusduke.tasklist.TaskList;
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
            + "Wakanda forever! I'm Winston Duke"
            + "What can I do for you?";
    public static final String MESSAGE_EMPTY_INPUT = "Empty input detected, please re-enter\n";
    public static final String MESSAGE_INDEX_NOT_NUMBER = "Index must be numbers!";
    public static final String MESSAGE_NO_TASK_DESCRIPTION = "Task description cannot be empty!\n";
    public static final String MESSAGE_NO_KEYWORD_FOR_EVENT_DEADLINE = "Please use keyword \"/\" to represent date and time. E.g. /at 6 Aug 2022 6pm\n";
    public static final String MESSAGE_TASK_NUMBER_NOT_EXIST = "Task number not exist!\n";
    public static final String MESSAGE_TASK_NUMBER_OUT_OF_RANGE = "Task number out of list range!\n";
    public static final String MESSAGE_NOT_A_TASK = "OOPS!!! I'm sorry, but I don't know what that means :-( \n"
                                                    + "Please use keywords: bye, list, mark, unmark, delete, todo, deadlines or events.\n";
    public static final String MESSAGE_NO_TIME_FOR_DEADLINE_EVENT = "Invalid command! Date and time for deadline or event cannot be empty.\n";
    public static final String MESSAGE_NOT_TIMESLOT_FOR_EVENT = "Please enter a timeslot for the event! Or use deadline instead. \n";
    public static final String MESSAGE_DUPLICATE_TASK = "Task exist!\n";
}
