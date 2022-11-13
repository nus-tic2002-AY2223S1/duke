package duke;
import java.util.Scanner;

/**
 * This Ui class handles all user input/output and has the necessary formatting functionality.
 * It should be closed properly at the end of the program using close().
 */
public class Ui implements AutoCloseable {
    public static final String LONG_LINE = "------------------------------------------------------------";
    // Default indentation is 8 whitespaces
    public static final char DEFAULT_INDENT_CHARACTER = '\t';
    public static final int DEFAULT_INDENT_COUNT = 1;
    // Used for internal 2nd-level indentation (e.g. print task)
    public static final String INTERNAL_INDENT = "\t";

    protected String indent;
    protected Scanner scanner;

    /**
     * Constructor of Ui class
     * When no arguments are supplied, default indent and locale setting are used
     */
    public Ui() {
        this(DEFAULT_INDENT_CHARACTER, DEFAULT_INDENT_COUNT);
    }

    /**
     * Constructor of Ui class
     * @param indentCharacter The specific character for indentation
     * @param indentCount Number of `indentCharacter` to be used for indentation
     */
    public Ui(char indentCharacter, int indentCount) {
        indent = new String(new char[indentCount]).replace('\0', indentCharacter);
        scanner = new Scanner(System.in);
    }

    /**
     * Close the scanner instance initialized in constructor
     */
    @Override
    public void close() {
        scanner.close();
        scanner = null;
    }

    /**
     * Welcome text for the Gui
     */
    public String showWelcome() {
        return "Hello! I'm Anyer\nWhat can I do for you?";
    }


    /**
     * Read a line (delimited by EOL characters)
     * @return the line in the format of a string
     */
    public String read() {
        if (scanner == null || !scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    public void print(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            System.out.println(indent + line);
        }
    }

    /**
     * Print a message for a successful insertion of task
     */
    // Print a message for a successful insertion of task
    public void printNewTask(TaskList tasks) {
        int size = tasks.size();
        print(
                "Great. We added a new task:\n"
                        + INTERNAL_INDENT + tasks.get(size - 1) + "\n"
                        + String.format("You have in total %d tasks", size)
        );
    }

    /**
     * Print out an ordinary task list (with no additional information)
     * @param tasks The task list to be printed
     */
    public void printTaskList(TaskList tasks) {
        printTaskList(tasks, null);
    }

    /**
     * Print out a task list, showing the at/by time specified
     */
    public void printTaskList(TaskList tasks, DateTime dateTime) {
        if (tasks.size() == 0) {
            print("You don't have a task in your list!");
            return;
        }
        if (dateTime == null) {
            print("Here are the tasks in your list:");
        } else {
            print("Here are the tasks in your list at/by " + dateTime);
        }
        for (int i = 0; i < tasks.size(); i += 1) {
            print(String.format("%d.%s%s", i + 1, INTERNAL_INDENT, tasks.get(i)));
        }
    }

    /**
     * Print a long horizontal line
     */
    public void printLine() {
        print(LONG_LINE);
    }

    /**
     * Print an exception related to save-file loading exception
     * @param filepath Path of the save-file
     * @param e The exception object
     */
    public void printLoadSaveException(String filepath, Exception e) {
        printException(e);
        print(
                "Got a problem when loading save file at \'" + filepath + "\'.\n"
                        + "An empty list will be used instead!"
        );
        printLine();
    }

    public void printException(Exception e) {
        print("Oops! " + e.getMessage());
    }

    /**
     * Print a welcome message
     */
    public void printWelcome() {
        String logo =

                "     ___  _   ___   _____________ \n" +
                "    / _ \\| \\ | \\ \\ / |  ___| ___ \\\n" +
                "   / /_\\ |  \\| |\\ V /| |__ | |_/ /\n" +
                "   |  _  | . ` | \\ / |  __||    / \n" +
                "   | | | | |\\  | | | | |___| |\\ \\ \n" +
                "   \\_| |_\\_| \\_/ \\_/ \\____/\\_| \\_|";
        System.out.println( logo);
        print(
                "Hello! I'm Anyer.\n"
                 + "What can I do for you?\n"
                + "When input a deadlinedate (& time), please use format like /by '2022-01-20 23:59'.\n"
                +"When input a eventdate (& time), please use format like /at '2022-01-20 23:59' /to '2022-01-20 23:59'."
        );
        printLine();
    }

    public void printGoodbye() {
        print("Bye. Hope to see you again soon!");
    }
}