import com.sun.source.util.TaskListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello there! I'm Duke\nWhat can I do for you?");

        List<Task> taskList = new ArrayList<>();
        int count = 0;

        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();

            // enter bye to end chat
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // to list all items
            else if (line.equals("list")) {
                int taskCount = 0;
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                for (Task task : taskList) {
                    System.out.println(taskCount + 1 + ". " + task);
                    taskCount++;
                }
                System.out.println("____________________________________________________________");
            }

            // mark items
            else if (line.startsWith("mark ")) {

                String[] markedItem = line.split("mark ");
                int markedIndex = Integer.parseInt(markedItem[1]);
                Task markedTask = taskList.get(markedIndex - 1);
                markedTask.markAsDone();
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done: \n "+markedTask+
                        "\n____________________________________________________________" );
            }

            // unmarked items
            else if (line.startsWith("unmark ")) {
                String[] unmarkedItem = line.split("unmark ");
                int unmarkedIndex = Integer.parseInt(unmarkedItem[1]);
                Task unmarkedTask = taskList.get(unmarkedIndex - 1);
                unmarkedTask.markAsUndone();

                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet: \n" + unmarkedTask
                        + "\n____________________________________________________________");
            }

            // to do task
            else if (line.startsWith("todo")) {
                String[] todoInput = line.split("todo ");
                System.out.println("Got it. I've added this task:");
                String todoTask = todoInput[1];
                Todo task = new Todo(todoTask);
                taskList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            }

            // deadline task
            else if (line.startsWith("deadline")) {
                System.out.println("Got it. I've added this task:\n");
                String[] deadlineItemSplit = (line.substring(9)).split(" /by ");
                String deadlineTask = deadlineItemSplit[0];
                String deadlineBy = deadlineItemSplit[1];
                Deadline task = new Deadline(deadlineTask, deadlineBy);
                taskList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            }

            // event task
            else if (line.startsWith("event")) {
                System.out.println("Got it. I've added this task:\n");
                String[] eventItemSplit = (line.substring(6)).split(" /at ");
                String eventTask = eventItemSplit[0];
                String eventAt = eventItemSplit[1];
                Event task = new Event(eventTask, eventAt);
                taskList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            }

            // prompt user to enter valid input
            else {
                System.out.println("____________________________________________________________\n" +
                        "Please Enter a Valid Input (E.g. Todo Task 1)"+"" +
                        "\n____________________________________________________________");
            }

        }
    }
}