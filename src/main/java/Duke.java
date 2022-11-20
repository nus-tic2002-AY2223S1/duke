import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Duke.
 */
public class Duke {


    private static Scanner input = new Scanner(System.in);
    private Command command;
    private ArrayList<Task> tasks;

    /**
     * Instantiates a new Duke.
     */
    public Duke() {
        this.tasks = Storage.loadTasks();
        this.command = null;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Duke manager = new Duke();
//        manager.controls("todo borrow book");
//        manager.controls("todo borrow book to read");
//        manager.controls("deadline return book /by Sunday");
//        manager.controls("event project meeting /at Mon 2-4pm");
//        manager.controls("event project meeting /at Mon 4-6pm");
//
//        manager.controls("list");

        manager.showMenu();
    }

    /**
     * Show menu.
     */
    public void showMenu() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        String command = input.nextLine();
        this.controls(command);
    }

    /**
     * Controls.
     *
     * @param command the command
     */
    public void controls(String command) {
        try {

            String[] commands = command.split(" ");
            String commandType = commands[0];
            switch (commandType.toLowerCase()) {
                case "list":
                    list();
                    break;
                case "mark":
                    markComplete(commands);
                    break;
                case "todo":
                    tasks = new AddToDoCommand(tasks, command).execute();
                    break;
                case "event":
                    tasks = new AddEventCommand(tasks, command).execute();
                    break;
                case "deadline":
                    tasks = new AddDeadlineCommand(tasks, command).execute();
                    break;
                case "delete":
                    int index = Integer.parseInt(commands[1]);
                    delete(index);
                    break;
                case "find":
                    search(commands[1]);
                    break;
                case "bye":
                    System.exit(0);
                case "save":
                    Storage.saveTasks(tasks);
                    break;
                default:
                    System.out.println("Invalid Input Provided!");
                    break;
            }
        } catch (IllegalCommandException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException ee) {
            System.out.println("Invalid command entered!");
        } catch (DateTimeParseException ee) {
            System.out.println("Please enter a valid date format");
        }

        System.out.println();
        this.showMenu();
    }

    private void search(String word) {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(word)) {
                System.out.println("\t" + count + "." + task);
            }
        }
        System.out.println("-------------------------------------------------------------------");

    }

    /**
     * Mark complete.
     *
     * @param commands the commands
     */
    public void markComplete(String[] commands) {
        String str = "";
        for (int i = 1; i < commands.length; i++) {
            str += commands[i] + " ";
        }

        str = str.trim();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().equalsIgnoreCase(str)) {
                System.out.println("\n-------------------------------------------------------------------");
                task.markAsDone();
                System.out.println("\tGot it. I've marked this task:\n\t" + task);
                System.out.println("-------------------------------------------------------------------");
            }
        }
    }

    /**
     * List.
     */
    public void list() {
        System.out.println("\n-------------------------------------------------------------------");
        if (tasks.size() == 0) {
            System.out.println("No task added yet");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println("\t" + (i + 1) + "." + task);
            }
        }
        System.out.println("-------------------------------------------------------------------");
    }

    /**
     * Delete.
     *
     * @param index the index
     */
    public void delete(int index) {
        index--;
        if (index > 0 && index < tasks.size()) {
            System.out.println("Noted. I've removed this task:");
            Task task = tasks.remove(index);
            System.out.println(task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }

    }
}
