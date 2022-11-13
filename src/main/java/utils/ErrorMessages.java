package utils;

public class ErrorMessages {
    public static final String MARK_UNMARK_TASK_ERR_MSG =
            "Please use a number to mark/unmark a task\n" +
            "e.g.: \"mark 2\"";
    public static final String NUMBER_OUT_OF_RANGE_ERR_MSG =
            "Please input a number that is within the range of tasks";
    public static final String CREATE_TODO_ERR_MSG =
            "TODO was NOT created successfully";
    public static final String CREATE_TODO_NO_DESCRIPTION_ERR_MSG =
            CREATE_TODO_ERR_MSG + "\n" +
                    "TODO must have a description";
    public static final String CREATE_DEADLINE_ERR_MSG =
            "DEADLINE was NOT created successfully";
    public static final String CREATE_DEADLINE_NO_BY_ERR_MSG =
            CREATE_DEADLINE_ERR_MSG + "\nDEADLINE must have \"/by\"";
    public static final String CREATE_DEADLINE_MORE_THAN_ONE_BY_ERR_MSG =
            CREATE_DEADLINE_ERR_MSG + "\nDEADLINE must have ONLY ONE \"/by\"";
    public static final String CREATE_DEADLINE_NO_BY_DETAILS_ERR_MSG =
            CREATE_DEADLINE_ERR_MSG + "\n" +
                    "DEADLINE must have description before \"/by\"\n" +
                    "DEADLINE must have due date after \"/by\"";
    public static final String CREATE_DEADLINE_INVALID_DATE_ERR_MSG =
            CREATE_DEADLINE_ERR_MSG + "\nDEADLINE DATE must be in the format \"yyyy-mm-dd\" (e.g. 1995-12-26)";
    public static final String UPDATE_DEADLINE_ERR_MSG =
            "DEADLINE was NOT updated successfully";
    public static final String UPDATE_DEADLINE_NO_TO_ERR_MSG =
            UPDATE_DEADLINE_ERR_MSG + "\nDEADLINE must have \"/to\"";
    public static final String UPDATE_DEADLINE_MORE_THAN_ONE_TO_ERR_MSG =
            UPDATE_DEADLINE_ERR_MSG + "\nDEADLINE must have ONLY ONE \"/to\"";
    public static final String UPDATE_DEADLINE_NO_TO_DETAILS_ERR_MSG =
            UPDATE_DEADLINE_ERR_MSG + "\n" +
                    "DEADLINE must have index before \"/to\"\n" +
                    "DEADLINE must have due date after \"/to\"";
    public static final String UPDATE_DEADLINE_IS_NOT_DEADLINE_ERR_MSG =
            UPDATE_DEADLINE_ERR_MSG + "\nTask selected is NOT a deadline";
    public static final String CREATE_EVENT_ERR_MSG =
            "EVENT was NOT created successfully";
    public static final String CREATE_EVENT_NO_AT_ERR_MSG =
            CREATE_EVENT_ERR_MSG + "\nEVENT must have \"/at\"";
    public static final String CREATE_EVENT_MORE_THAN_ONE_AT_ERR_MSG =
            CREATE_EVENT_ERR_MSG + "\nEVENT must have ONLY ONE \"/at\"";
    public static final String CREATE_EVENT_NO_AT_DETAILS_ERR_MSG =
            CREATE_DEADLINE_ERR_MSG + "\n" +
                    "EVENT must have description before \"/at\"\n" +
                    "EVENT must have duration after \"/at\"";
    public static final String INVALID_INTEGER_ERR_MSG = "Please input a valid integer.";
    public static final String UNKNOWN_ERR_MSG = "Something went wrong.";



    public static String whatAreYouDoingErrMsg(String input) {
        return "Everybody needs help sometimes. Just enter \"help\" :D\n" +
                "This is what you entered:\n" + input;
    }

}
