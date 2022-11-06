package common.constants;

public class CommonConstant {
    // integer
    public static final int ZERO_VAL = 0;

    public static final int INIT_INT_VAL = 1;

    // string
    public static final String ADDED = "%s has been successfully added to the task list.";

    public static final String AT = "at";

    public static final String BY = "by";

    public static final String BYE_GREETING = "Bye! Hope to see you again soon!";

    public static final String CLOSE_BRACKET = ")";

    public static final String CLOSE_SQ_BRACKET = "]";

    public static final String DASHES = new String(new char[120]).replace("\0", "-");

    public static final String DEADLINE_BY = "by: ";

    public static final String DELETED = "%s has been successfully deleted to the task list.";

    public static final String DELIMITER = "/";

    public static final String EVENT_AT = "at: ";

    public static final String HELLO_GREETING = "Hello! I'm Duke! How can I help you? (Type 'help' for command usage)";

    public static final String INPUT_OPTIONS =
            "-----------------------------------------------------------------------------------------------------------------------------\n" +
            "| S/N | Command                                    | Description                          | Usage Example                   |\n" +
            "|-----|--------------------------------------------|--------------------------------------|---------------------------------|\n" +
            "| 1   | deadline <task> /by <date>                 | Add deadline with date in yyyy-mm-dd | deadline taskABC /by 2022-12-31 |\n" +
            "| 2   | event <task> /at <date>                    | Add event with date in yyyy-mm-dd    | event taskABC /at 2022-12-31    |\n" +
            "| 3   | todo <task>                                | Add todo task                        | todo taskABC                    |\n" +
            "| 4   | list                                       | List existing tasks                  | list                            |\n" +
            "| 5   | mark <task_no>                             | Mark specified task                  | mark 1                          |\n" +
            "| 6   | unmark <task_no>                           | Unmark specified task                | unmark 1                        |\n" +
            "| 7   | delete <task_no>                           | Delete specified task                | delete 1                        |\n" +
            "| 8   | find <keyword>                             | Find keyword in task list            | find meeting                    |\n" +
            "| 9   | update <task_no> <desc/date> <update_desc> | Update task by desc/date             | update 1 date 2022-12-31        |\n" +
            "| 10  | bye                                        | Exit duke bot                        | bye                             |\n" +
            "-----------------------------------------------------------------------------------------------------------------------------";

    public static final String LOGO = "____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String MARKED_ICON = "X";

    public static final String MARKED_TASK = "Nice! Task %s is successfully marked.";

    public static final String MATCHING_TASK_LIST = "Here are the matching tasks in your list: ";

    public static final String OPEN_BRACKET = "(";

    public static final String OPEN_SQ_BRACKET = "[";

    public static final String PERIOD = ".";

    public static final String PIPE = "|";

    public static final String PROMPT = "bot@duke:~$ ";

    public static final String SPACE = " ";

    public static final String TAB = "\t";

    public static final String TASK_LIST = "Here are the tasks in your task list:";

    public static final String TOTAL_NO_OF_TASKS = "Now you have %o task(s) in the list!";

    public static final String UNMARKED_TASK = "Okay, task %s is successfully unmarked.";

    public static final String UPDATED_TASK = "Task has been updated successfully!";
}
