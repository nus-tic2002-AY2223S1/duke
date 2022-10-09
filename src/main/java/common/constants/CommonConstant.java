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

    public static final String DASHES = new String(new char[120]).replace("\0", "-");

    public static final String DEADLINE_BY = "by: ";

    public static final String EVENT_AT = "at: ";

    public static final String HELLO_GREETING = "Hello! I'm Duke! How can I help you?";

    public static final String INPUT_OPTIONS =
            "\t---------------------------------------------------------------------------------------------------------\n" +
            "\t| S/N | Command                           | Description                   | Usage Example               |\n" +
            "\t|-----|-----------------------------------|-------------------------------|-----------------------------|\n" +
            "\t| 1   | <task>                            | Add task                      | taskABC                     |\n" +
            "\t| 2   | deadline <task> /by <date & time> | Add deadline with date & time | deadline taskABC /by Sunday |\n" +
            "\t| 3   | event <task> /at <date & time>    | Add event with date & time    | event taskABC /at Mon 2-4pm |\n" +
            "\t| 4   | todo <task>                       | Add todo task                 | todo taskABC                |\n" +
            "\t| 5   | list                              | List existing tasks           | list                        |\n" +
            "\t| 6   | mark <task>                       | Mark specified task           | mark taskABC                |\n" +
            "\t| 7   | unmark <task>                     | Unmark specified task         | unmark taskABC              |\n" +
            "\t| 8   | bye                               | Exit duke bot                 | bye                         |\n" +
            "\t---------------------------------------------------------------------------------------------------------";

    public static final String LOGO = "\t\t\t\t\t\t\t\t\t ____        _        \n"
            + "\t\t\t\t\t\t\t\t\t|  _ \\ _   _| | _____ \n"
            + "\t\t\t\t\t\t\t\t\t| | | | | | | |/ / _ \\\n"
            + "\t\t\t\t\t\t\t\t\t| |_| | |_| |   <  __/\n"
            + "\t\t\t\t\t\t\t\t\t|____/ \\__,_|_|\\_\\___|\n";

    public static final String MARKED_TASK = "Nice! Task %s is successfully marked.";

    public static final String PROMPT = "bot@duke:~$ ";

    public static final String SPACE = " ";

    public static final String TAB = "\t";

    public static final String TASK_LIST = "Here are the tasks in your task list:";

    public static final String TOTAL_NO_OF_TASKS = "Now you have %o task(s) in the list!";

    public static final String UNMARKED_TASK = "Okay, task %s is successfully unmarked.";
}
