package utils;

public class ErrorMessages {
    public static final String MARK_UNMARK_TASK_ERR_MSG =
            "Please use a number to mark/unmark a task\n" +
            "e.g.: \"mark 2\"";

    public static final String MARK_UNMARK_NUMBER_OUT_OF_RANGE_ERR_MSG =
            "Please input a number that is within the range of tasks";

    public static final String NO_DESCRIPTION_ERR_MSG =
            "No description was detected for the task";

    public static final String CREATE_TASK_ERR_MSG =
            "TASK was not created successfully";

    public static final String CREATE_TODO_ERR_MSG =
            "TODO was not created successfully";

    public static final String CREATE_DEADLINE_ERR_MSG =
            "DEADLINE was not created successfully";

    public static final String CREATE_DEADLINE_NO_BY_ERR_MSG =
            CREATE_DEADLINE_ERR_MSG + "\nDEADLINE must have \"/by\"";

    public static final String CREATE_DEADLINE_NO_TEXT_AFTER_BY_ERR_MSG =
            CREATE_DEADLINE_ERR_MSG + "\nDEADLINE must have text after \"/by\"";

    public static final String CREATE_EVENT_ERR_MSG =
            "EVENT was not created successfully";

    public static final String CREATE_EVENT_NO_AT_ERR_MSG =
            CREATE_EVENT_ERR_MSG + "\nEVENT must have \"/at\"";

    public static final String CREATE_EVENT_NO_TEXT_AFTER_AT_ERR_MSG =
            CREATE_EVENT_ERR_MSG + "\nEVENT must have text after \"/at\"";

}
