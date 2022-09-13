import java.util.Scanner;

public class Duke {
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

        String[] tasksArray = new String[100];

       // Task t = new Task();

        inputCommand(tasksArray, 0);
    }
    public static void inputCommand(String[] tasksArr, int count) {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if (command.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }

        else if (command.equalsIgnoreCase("list")) {
            int listNo = 0;

            System.out.println("Here are the tasks in your list:");

            for (String element : tasksArr) {
                listNo++;
                if (element == null) {
                    System.out.println();
                    break;
                } else
                    System.out.println(listNo + "." + element);
            }

            inputCommand(tasksArr, count);
        }

        else if (command.startsWith("mark")) {
            int taskNo = Integer.parseInt(command.substring(5)) - 1;
            String taskName = tasksArr[taskNo].substring(4);

            Task t = new Task(taskName);
            t.markAsDone(true);

            tasksArr[taskNo] = "[" + t.getStatusIcon() + "] " + taskName;
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                    + tasksArr[taskNo] + System.lineSeparator());

            inputCommand(tasksArr, count);
        }

        else if (command.startsWith("unmark")) {
            int taskNo = Integer.parseInt(command.substring(7)) - 1;
            String taskName = tasksArr[taskNo].substring(4);

            Task t = new Task(taskName);
            t.markAsDone(false);

            tasksArr[taskNo] = "[" + t.getStatusIcon() + "] " + taskName;
            System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                    + tasksArr[taskNo] + System.lineSeparator());

            inputCommand(tasksArr, count);
        }

        else {
            System.out.println("added: " + command + System.lineSeparator());

            tasksArr[count] = "[ ] " + command;

            inputCommand(tasksArr,count + 1);
        }
    }
}
