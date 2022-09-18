package duke;

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
public class Duke {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        //data add
        Vector<Task> tasks = new Vector<>();
        greetingHandler();
        printlnWidthIndent(LONG_LINE);

        Scanner in = new Scanner(System.in);
        Boolean isExit = false;

        while(!isExit){
        String line = in.nextLine();
            // Split the line by any whitespaces characters (including spaces, tabs etc.)
        String[] arguments = line.split("\\s+");
            // If first argument (command) is empty, there are empty spaces typed in at the front - so we remove it
            if (arguments[0].isEmpty()) {
                arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            }

            printlnWidthIndent(LONG_LINE);

            try {
                switch(arguments[0]) {
                    case "bye":
                        byeHandler();
                        isExit = true;
                        break;
                    case "list":
                        listHandler(tasks);
                        break;
                    case "done":
                        doneHandler(tasks, arguments);
                        break;
                    case "notdone":
                        notdone(tasks, arguments);
                        break;
                    case "deadline":
                        deadlineHandler(tasks, arguments);
                        break;
                    case "event":
                        eventHandler(tasks, arguments);
                        break;
                    case "todo":
                        todoHandler(tasks, arguments);
                        break;
                    default:
                        throw new InvalidInputException(InputExceptionType.UNKNOWN_COMMAND);
                }
            } catch (Exception e) {
                printlnWidthIndent ("Oops! " + e.getMessage());

        }
            printlnWidthIndent(LONG_LINE);
        }
        in.close();
    }

        protected static void greetingHandler() {
        String logo = "    =    |\\    | |     | ======   |=====\n"
                    + "   / \\   | \\   |  |   |  ||       |     =\n"
                    + "  /___\\  |  \\  |   | |   ||====   |=====\n"
                    + " /-----\\ |   \\ |    |    ||       |   \\\n"
                    + "/       \\|    \\|    |    ======   |    \\\n";
        System.out.println( logo);
            printlnWidthIndent(LONG_LINE);
            printlnWidthIndent("Hello! I am Anyer");
            printlnWidthIndent("What can I do for you?");
            printlnWidthIndent(LONG_LINE);
    }
    //add"good bye "
        protected static void byeHandler(){

            printlnWidthIndent("Bye. Hope to see you again soon!");
    }

    protected static void printLine(String line){
        printlnWidthIndent(LONG_LINE);
        printlnWidthIndent(line);
        printlnWidthIndent(LONG_LINE);
    }
    protected static void listHandler(Vector<Task> tasks) {
        printlnWidthIndent("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            printlnWidthIndent(String.format("%d.\t%s", i + 1, tasks.get(i)));
        }
    }

    // Mark a task to be done with index specified in arguments[1]
    protected static void doneHandler(Vector<Task> tasks, String[] arguments) throws InvalidInputException {
        if (arguments.length < 2) {
            // An index must be provided for the task to be marked "done"
            printlnWidthIndent("You will need to give me an index, like this: `done 2`.");
        } else {
            try {
                int index = Integer.parseInt(arguments[1]);
                if (index > tasks.size() || index < 1) {
                    // This index is out of the boundary of our database
                    throw new InvalidInputException(InputExceptionType.INDEX_OUT_OF_BOUND);
                }

                Task task = tasks.get(index - 1);
                task.markAsDone();
                tasks.set(index - 1, task);

                printlnWidthIndent("Nice! I've marked this task as done:");
                printlnWidthIndent("\t" + task);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(InputExceptionType.NOT_INTEGER, e);
            }
        }
    }

    protected static void notdone(Vector<Task> tasks, String[] arguments) throws InvalidInputException{
        if (arguments.length < 2) {
            // An index must be provided for the task to be marked "done"
            printlnWidthIndent("You will need to give me an index, like this: `ndone 2`.");
        } else {
            try {
                int index = Integer.parseInt(arguments[1]);
                if (index > tasks.size() || index < 1) {
                    // This index is out of the boundary of our database
                    throw new InvalidInputException(InputExceptionType.INDEX_OUT_OF_BOUND);
                }

                Task task = tasks.get(index - 1);
                task.markAsnotDone();
                tasks.set(index - 1, task);

                printlnWidthIndent("Nice! I've unmarked this task as ndone:");
                printlnWidthIndent("\t" + task);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(InputExceptionType.NOT_INTEGER, e);
            }
        }
    }
    // Create a deadline task
    protected static void deadlineHandler(Vector<Task> tasks, String[] arguments) throws InvalidInputException {
        int i = findIndex(arguments, "/by");
        if (i != -1 && i + 1 != arguments.length) {
            String description = String.join(" ", Arrays.copyOfRange(arguments, 1, i));
            String by = String.join(" ", Arrays.copyOfRange(arguments, i + 1, arguments.length));
            tasks.add(new Deadline(description, by));
            printNewTask(tasks);
        } else {
            throw new InvalidInputException(InputExceptionType.NO_AT_DATE);
        }
    }

    protected static void eventHandler(Vector<Task> tasks, String[] arguments) throws InvalidInputException {
        int i = findIndex(arguments, "/at");
        if (i != -1 && i + 1 != arguments.length) {
            String description = String.join(" ", Arrays.copyOfRange(arguments, 1, i));
            String at = String.join(" ", Arrays.copyOfRange(arguments, i + 1, arguments.length));
            tasks.add(new Event(description, at));
            printNewTask(tasks);
        } else {
            throw new InvalidInputException(InputExceptionType.NO_AT_DATE);
        }
    }
    // Create an event task
    protected static void todoHandler(Vector<Task> tasks, String[] arguments) throws InvalidInputException {
        String description = String.join(" ", Arrays.copyOfRange(arguments, 1, arguments.length));
        tasks.add(new ToDo(description));
        printNewTask(tasks);
    }

    // Print a line with 4 spaces as indentation
    protected static void printlnWidthIndent(String line) {
        System.out.println("\t" + line);
    }
    // Print a message for a successful insertion of task
    protected static void printNewTask(Vector<Task> tasks) {
        int size = tasks.size();
        printlnWidthIndent("Great. We added a new task:");
        printlnWidthIndent("\t" + tasks.get(size - 1));
        printlnWidthIndent(String.format("You have in total %d tasks", size));
    }
    // Find the index of a string in a string array
    protected static int findIndex(String[] haystack, String needle) {
        for (int i = 0; i < haystack.length; i += 1) {
            if (haystack[i].equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
