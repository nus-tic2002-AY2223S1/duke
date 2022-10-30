import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    //private static final Task[] tasks = new Task[100];
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String hello = "Hello! I'm Rick\n"
                + "What can I do for you?\n\n";
        System.out.print(hello);

        inputCommand();
    }
    public static void inputCommand(){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        System.out.println("taskList: " + tasks);

        try {
            if (command.replaceAll("\\s+","").equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            }

            else if (command.replaceAll("\\s+","").equalsIgnoreCase("list")) {
                int listNo = 0;
                System.out.println("Here are the tasks in your list:");

                for (Task element : tasks) {
                    listNo++;
                    if (element == null) {
                        System.out.println();
                        break;
                    } else
                        System.out.println(listNo + "." + element);
                }
                System.out.println();
                inputCommand();
            }

            else if (command.toLowerCase().startsWith("mark")) {
                try {
                    // -5 index for "mark "
                    int taskNo = Integer.parseInt(command.substring(5)) - 1;
                    tasks.get(taskNo).markAsDone(true);

                    System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                            + tasks.get(taskNo).toString() + System.lineSeparator());
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate task to mark." + System.lineSeparator());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("☹ OOPS!!! Task not found or invalid. Please try again." + System.lineSeparator());
                }
                inputCommand();
            }

            else if (command.toLowerCase().startsWith("unmark")) {
                try {
                    // -7 index for "unmark "
                    int taskNo = Integer.parseInt(command.substring(7)) - 1;
                    tasks.get(taskNo).markAsDone(false);

                    System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                            + tasks.get(taskNo).toString() + System.lineSeparator());
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate task to unmark." + System.lineSeparator());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("☹ OOPS!!! Task not found or invalid. Please try again." + System.lineSeparator());
                }
                inputCommand();
            }

            else if (command.startsWith("delete")) {
                try {
                    // -7 index for "unmark"
                    int taskNo = Integer.parseInt(command.substring(7)) - 1;
                    removeTask(tasks.get(taskNo));
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate task to delete." + System.lineSeparator());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("☹ OOPS!!! Task not found or invalid. Please try again." + System.lineSeparator());
                }
                inputCommand();
            }

            else if (command.startsWith("todo")) {
                try {
                    String taskName = command.substring(5);
                    if (taskName.isBlank()) {
                        throw new DukeException();
                    } else {
                        addTask(new Todo(taskName));
                    }
                } catch (StringIndexOutOfBoundsException | DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty." + System.lineSeparator());
                }
                inputCommand();
            }

            else if (command.startsWith("deadline")) {
                try {
                    int split = command.indexOf('/');
                    String taskName = command.substring(9, split - 1);
                    String byWhen = command.substring(split + 4);

                    addTask(new Deadline(taskName, byWhen));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty." + System.lineSeparator());
                }
                inputCommand();
            }

            else if (command.startsWith("event")) {
                try {
                    int split = command.indexOf('/');
                    String taskName = command.substring(6, split - 1);
                    String byWhen = command.substring(split + 4);

                    addTask(new Event(taskName, byWhen));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty." + System.lineSeparator());
                }
                inputCommand();
            }

            else {
                throw new DukeException();
            }

        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator());
            inputCommand();
        }
    }

    public static void addTask(Task t){
        tasks.add(t);

        System.out.println("Got it. I've added this task:" + System.lineSeparator() +
                t.toString() + System.lineSeparator() + "Now you have " + tasks.size() + " task(s) in the list." +
                System.lineSeparator());
    }

    public static void removeTask(Task t){
        tasks.remove(t);

        System.out.println("Noted. I've removed this task:" + System.lineSeparator() +
                t.toString() + System.lineSeparator() + "Now you have " + tasks.size() + " task(s) in the list." +
                System.lineSeparator());
    }
}