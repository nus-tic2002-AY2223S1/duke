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
                    System.out.println(taskCount + 1 + ". [" + task.getStatusIcon() + "]   " + task.getDescription());
                    taskCount++;
                }
                System.out.println("____________________________________________________________");
            }

            // mark items
            else if (line.startsWith("mark ")) {
                String markedItem = line;
                if (line.length() > 5) {
                    int markIndex = Integer.parseInt(markedItem.substring(5));
                    Task markedTask = taskList.get(markIndex - 1);
                    markedTask.markAsDone();
                    System.out.println("____________________________________________________________\n" +
                            "Nice! I've marked this task as done: \n   [" + markedTask.getStatusIcon() + "]   " + markedTask.getDescription()+
                            "\n____________________________________________________________" );
                } else
                    System.out.println("Please enter a Task Number.(E.g. mark 1)\n____________________________________________________________");
            }

            // unmarked items
            else if (line.startsWith("unmark ")) {
                String markString = line;
                if (line.length() > 7) {
                    int markIndex = Integer.parseInt(markString.substring(7));
                    Task markedTask = taskList.get(markIndex - 1);
                    markedTask.markAsUndone();
                    System.out.println("____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet: \n   [" + markedTask.getStatusIcon() + "]   " + markedTask.getDescription()
                    + "\n____________________________________________________________");

                }
            }

            // add to list
            else {
                Task newTask = new Task(line);
                taskList.add(newTask);

                System.out.println("____________________________________________________________\nadded: " + line + "\n____________________________________________________________\n");
            }

        }
    }
}