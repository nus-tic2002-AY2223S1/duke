import com.sun.source.util.TaskListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> taskList = new ArrayList<>();

    public static void listTask() {
        int taskCount = 0;
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(taskCount + 1 + ". " + task);
            taskCount++;
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTask(String[] lineSpaceSplit) {
        int markedIndex = Integer.parseInt(lineSpaceSplit[1]);
        try {
            Task markedTask = taskList.get(markedIndex - 1);
            markedTask.markAsDone();
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("____________________________________________________________" +
                    "Task Number out of range. Please enter a valid input (Task Number: 1 " + "-" + taskList.size()+")"+
                    "____________________________________________________________");
        }
    }

    public static void unmarkTask(String[] lineSpaceSplit) {
        int unmarkedIndex = Integer.parseInt(lineSpaceSplit[1]);
        try {
            Task unmarkedTask = taskList.get(unmarkedIndex - 1);
            unmarkedTask.markAsUndone();
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("____________________________________________________________" +
                    "Task Number out of range. Please enter a valid input (Current Range: 1 " + "to " + taskList.size()+")"+
                    "____________________________________________________________");
        }
    }

    public static void todoTask(String line) {
        System.out.println("____________________________________________________________");
        try {
            String todoTask = line.substring(5);
            Todo newTodoTask = new Todo(todoTask);
            taskList.add(newTodoTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTodoTask);
            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            System.out.println("Todo description cannot be empty.\nPlease enter a valid input (E.g. todo borrow book).");
        }
        System.out.println("____________________________________________________________");
    }

    public static void deadlineTask(String line) {

        System.out.println("____________________________________________________________");
        try {
            String[] deadlineItemSplit = (line.substring(9)).split(" /by ");
            String deadlineTask = deadlineItemSplit[0];
            String deadlineBy = deadlineItemSplit[1];
            Deadline task = new Deadline(deadlineTask, deadlineBy);
            taskList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            System.out.println("Invalid Deadline input.\nPlease enter a valid input (E.g. deadline return book /by Sunday).");
        }
        System.out.println("____________________________________________________________");
    }

    public static void eventTask(String line) {
        System.out.println("____________________________________________________________");
        try {
            String[] eventItemSplit = (line.substring(6)).split(" /at ");
            String eventTask = eventItemSplit[0];
            String eventAt = eventItemSplit[1];
            Event task = new Event(eventTask, eventAt);
            taskList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            System.out.println("Invalid Event input.\nPlease enter a valid input (E.g. event project meeting /at Mon 2-4pm).");
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello there! I'm Duke\nWhat can I do for you?");


        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            String[] lineSpaceSplit = line.split(" ");

            // enter bye to end chat
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // to list all items
            else if (line.equals("list")) {
                listTask();
            }

            // mark items
            else if (line.startsWith("mark")) {
                if (taskList.isEmpty()){
                    System.out.println("____________________________________________________________\n" +
                            "No Task is found. Please create a task." +
                            "\n____________________________________________________________");
                }
                else {
                    try {
                        markTask(lineSpaceSplit);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("____________________________________________________________\n" +
                                "Task Number not detected. Please enter a valid input (E.g. mark 1)" +
                                "\n____________________________________________________________");
                    }
                }
            }

            // unmarked items
            else if (line.startsWith("unmark")) {
                if (taskList.isEmpty()){
                    System.out.println("____________________________________________________________\n" +
                            "No Task is found. Please create a task." +
                            "\n____________________________________________________________");
                }
                else {
                    try {
                        unmarkTask(lineSpaceSplit);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("____________________________________________________________\n" +
                                "No Task Number detected. Please enter a valid input (E.g. unmark 1)" +
                                "\n____________________________________________________________");
                    }
                }
            }

            // to do task
            else if (line.startsWith("todo")) {
                todoTask(line);
            }

            // deadline task
            else if (line.startsWith("deadline")) {
                deadlineTask(line);
            }

            // event task
            else if (line.startsWith("event")) {
                eventTask(line);
            }

            // prompt user to enter valid input
            else {
                System.out.println("____________________________________________________________\n" +
                        "Please enter a valid input (E.g. todo Task 1)" + "" +
                        "\n____________________________________________________________");
            }
        }
    }
}