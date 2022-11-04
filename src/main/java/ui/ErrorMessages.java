package ui;

import taskList.TaskList;

public class ErrorMessages {
    public static final String TASK_NUMBER_OOB = "!!ERROR!! Task Number out of range. Please enter a valid input (Current Range: 1 " + "to " + TaskList.taskList.size() + ")";
    public static final String INVALID_EVENT_INPUT = "!!ERROR!! Invalid Event input.\nPlease enter a valid input (E.g. event project meeting /at Mon 2-4pm).";
    public static final String INVALID_DEADLINE_INPUT = "!!ERROR!! Invalid Deadline input.\nPlease enter a valid input (E.g. deadline return book /by Sunday).";
    public static final String INVALID_TODO_INPUT = "!!ERROR!! Invalid Todo input.\nPlease enter a valid input (E.g. todo borrow book).";

    public static final String LOADING_ERROR = "!!ERROR!! File not found.";
    public static final String NO_TASK_FOUND = "!!ERROR!! No Task is found. Please create a task.";
    public static final String INVALID_TASK_NUMBER = "!!ERROR!! Task number not detected. Please enter a valid input.";
}

