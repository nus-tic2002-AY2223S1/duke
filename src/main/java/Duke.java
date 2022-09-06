import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
public class Duke {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        //data add
        Vector<Task> tasks = new Vector<>();
        greeting();
        printIndent(LONG_LINE);

        Scanner in = new Scanner(System.in);
        Boolean exit = false;

        while(!exit){
        String line = in.nextLine();
        String[] arguments = line.split(" ");

        printIndent(LONG_LINE);

            switch(arguments[0]) {
            case"bye":
                goodbye();
                exit = true;
                break;
        //list
            case"list":
                list(tasks);
                break;
        //done
            case "mark":
                done(tasks, arguments);
                break;
            case "deadline":
                    deadline(tasks, arguments);
                    break;
            case "event":
                    event(tasks, arguments);
                    break;
            case "todo":
                    todo(tasks, arguments);
                    break;
            default:
                save(tasks, line);

        }
            printIndent(LONG_LINE);
        }
        in.close();
    }

        protected static void greeting() {
        String logo = "    =    |\\    | |     | ======   |=====\n"
                    + "   / \\   | \\   |  |   |  ||       |     =\n"
                    + "  /___\\  |  \\  |   | |   ||====   |=====\n"
                    + " /-----\\ |   \\ |    |    ||       |   \\\n"
                    + "/       \\|    \\|    |    ======   |    \\\n";
        System.out.println( logo);
        printIndent(LONG_LINE);
        printIndent("Hello! I am Anyer");
        printIndent("What can I do for you?");
        printIndent(LONG_LINE);
    }
    //add"good bye "
        protected static void goodbye(){

            printIndent("Bye. Hope to see you again soon!");
    }

    protected static void printLine(String line){
        printIndent(LONG_LINE);
        printIndent(line);
        printIndent(LONG_LINE);
    }
    protected static void list(Vector<Task> tasks) {
        printIndent("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            printIndent(String.format("%d.\t%s", i + 1, tasks.get(i)));
        }
    }

    // Mark a task to be done with index specified in arguments[1]
    protected static void done(Vector<Task> tasks, String[] arguments) {
        if (arguments.length < 2) {
            // An index must be provided for the task to be marked "done"
            printIndent("You will need to give me an index, like this: `done 2`.");
        } else {
            try {
                int index = Integer.parseInt(arguments[1]);
                if (index > tasks.size() || index < 1) {
                    // This index is out of the boundary of our database
                    throw new IllegalArgumentException();
                }

                Task task = tasks.get(index - 1);
                task.markAsDone();
                tasks.set(index - 1, task);

                printIndent("Nice! I've marked this task as done:");
                printIndent("\t" + task);
            } catch (NumberFormatException e) {
                printIndent("Index provided is not a proper number.");
            } catch (IllegalArgumentException e) {
                printIndent("Task with this index is not found in our database.");
            }
        }
    }
    // Create a deadline task
    protected static void deadline(Vector<Task> tasks, String[] arguments) {
        int i = findIndex(arguments, "/by");
        if (i != -1) {
            String description = String.join(" ", Arrays.copyOfRange(arguments, 1, i));
            String byTime = String.join(" ", Arrays.copyOfRange(arguments, i + 1, arguments.length));
            tasks.add(new Deadline(description, byTime));
            printNewTask(tasks);
        } else {
            printIndent("You must specify a deadline after /by");
        }
    }

    // Create an event task
    protected static void event(Vector<Task> tasks, String[] arguments) {
        int i = findIndex(arguments, "/at");
        if (i != -1) {
            String description = String.join(" ", Arrays.copyOfRange(arguments, 1, i));
            String byTime = String.join(" ", Arrays.copyOfRange(arguments, i + 1, arguments.length));
            tasks.add(new Event(description, byTime));
            printNewTask(tasks);
        } else {
            printIndent("You must specify a event time after /at");
        }
    }

    // Create a todo task
    protected static void todo(Vector<Task> tasks, String[] arguments) {
        tasks.add(new ToDo(String.join(" ", Arrays.copyOfRange(arguments, 1, arguments.length))));
        printNewTask(tasks);
    }

    // Save a new task in the task list
    protected static void save(Vector<Task> tasks, String description) {
        tasks.add(new Task(description));
        printNewTask(tasks);
    }

    // Print a line with 4 spaces as indentation
    protected static void printIndent(String line) {
        System.out.println("\t" + line);
    }
    // Print a message for a successful insertion of task
    protected static void printNewTask(Vector<Task> tasks) {
        int size = tasks.size();
        printIndent("Great. We added a new task:");
        printIndent("\t" + tasks.get(size - 1));
        printIndent(String.format("You have in total %d tasks", size));
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
