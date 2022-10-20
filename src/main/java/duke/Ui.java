package duke;
import java.util.Scanner;

public class Ui implements AutoCloseable {
    public static final String LONG_LINE = "------------------------------------------------------------";
    // Default indentation is 8 whitespaces
    public static final char DEFAULT_INDENT_CHARACTER = '\t';
    public static final int DEFAULT_INDENT_COUNT = 1;
    // Used for internal 2nd-level indentation (e.g. print task)
    public static final String INTERNAL_INDENT = "\t";

    protected String indent;
    protected Scanner scanner;

    public Ui() {
        this(DEFAULT_INDENT_CHARACTER, DEFAULT_INDENT_COUNT);
    }

    public Ui(char indentCharacter, int indentCount) {
        indent = new String(new char[indentCount]).replace('\0', indentCharacter);
        scanner = new Scanner(System.in);
    }

    @Override
    public void close() {
        scanner.close();
        scanner = null;
    }

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

    // Print a message for a successful insertion of task
    public void printNewTask(TaskList tasks) {
        int size = tasks.size();
        print(
                "Great. We added a new task:\n"
                        + INTERNAL_INDENT + tasks.get(size - 1) + "\n"
                        + String.format("You have in total %d tasks", size)
        );
    }

    public void printTaskList(TaskList tasks) {
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            print(String.format("%d.%s%s", i + 1, INTERNAL_INDENT, tasks.get(i)));
        }
    }

    public void printLine() {
        print(LONG_LINE);
    }

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
    public void printWelcome() {
        String logo = "    =    |\\    | |     | ======   |=====\n"
                + "   / \\   | \\   |  |   |  ||       |     =\n"
                + "  /___\\  |  \\  |   | |   ||====   |=====\n"
                + " /-----\\ |   \\ |    |    ||       |   \\\n"
                + "/       \\|    \\|    |    ======   |    \\\n";
        System.out.println( logo);
        print(
                "Hello! I'm Anyer.\n"
                        + "What can I do for you?"
        );
        printLine();
    }

    public void printGoodbye() {
        print("Bye. Hope to see you again soon!");
    }
}