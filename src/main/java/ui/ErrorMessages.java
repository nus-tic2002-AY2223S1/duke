package ui;

import taskList.TaskList;

public class ErrorMessages {
    public static final String TASK_NUMBER_OOB = "!!Error!! Task Number out of range. Please enter a valid input (Current Range: 1 " + "to " + TaskList.taskList.size() + ")";
    public static final String INVALID_EVENT_INPUT = "!!Error!! Invalid Event input.\nPlease enter a valid input (E.g. event project meeting /at Mon 2-4pm).";
    public static final String INVALID_DEADLINE_INPUT = "!!Error!! Invalid Deadline input.\nPlease enter a valid input (E.g. deadline return book /by Sunday).";
    public static final String INVALID_TODO_INPUT = "!!Error!! Invalid Todo input.\nPlease enter a valid input (E.g. todo borrow book).";
}

