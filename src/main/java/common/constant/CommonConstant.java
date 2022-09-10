package common.constant;

public class CommonConstant {
    // integer
    public static final int ZERO_VAL = 0;
    public static final int INIT_INT_VAL = 1;

    // string
    public static final String ADDED = "Task %s has been successfully added to the task list.";

    public static final String BYE_COMMAND = "bye";

    public static final String BYE_GREETING = "Bye! Hope to see you again soon!";

    public static final String CLOSE_SQ_BRACKET = "] ";

    public static final String DASHES = new String(new char[90]).replace("\0", "-");

    public static final String HELLO_GREETING = "Hello! I'm Duke! How can I help you?";

    public static final String INPUT_OPTIONS =
            "\t\t\t-------------------------------------------------------------------\n" +
            "\t\t\t| S/N | Command       | Description              | Usage Example  |\n" +
            "\t\t\t|-----|---------------|--------------------------|----------------|\n" +
            "\t\t\t| 1   | <task>        | Add task to task list    | taskABC        |\n" +
            "\t\t\t| 2   | list          | List tasks in task list  | list           |\n" +
            "\t\t\t| 3   | mark <task>   | Mark task in task list   | mark taskABC   |\n" +
            "\t\t\t| 4   | unmark <task> | Unmark task in task list | unmark taskABC |\n" +
            "\t\t\t| 5   | bye           | Exit duke bot            | bye            |\n" +
            "\t\t\t-------------------------------------------------------------------";

    public static final String LIST_COMMAND = "list";

    public static final String LOGO = "\t\t\t\t\t\t\t\t ____        _        \n"
            + "\t\t\t\t\t\t\t\t|  _ \\ _   _| | _____ \n"
            + "\t\t\t\t\t\t\t\t| | | | | | | |/ / _ \\\n"
            + "\t\t\t\t\t\t\t\t| |_| | |_| |   <  __/\n"
            + "\t\t\t\t\t\t\t\t|____/ \\__,_|_|\\_\\___|\n";

    public static final String MARK_COMMAND = "mark";

    public static final String MARKED_ICON = "X";

    public static final String OPEN_SQ_BRACKET = "[";

    public static final String PERIOD = ". ";

    public static final String PROMPT = "bot@duke:~$ ";

    public static final String SPACE = " ";

    public static final String TASK_LIST = "Here are the tasks in your task list:";

    public static final String MARKED_TASK = "Nice! Task %s is successfully marked.";

    public static final String UNMARKED_TASK = "Okay, task %s is successfully unmarked.";

    public static final String UNMARK_COMMAND = "unmark";
}
