import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

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

        inputCommand(tasks, 0);
    }
    public static void inputCommand(Task[] tasksArr, int count) {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if (command.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }

        else if (command.equalsIgnoreCase("list")) {
            int listNo = 0;

            System.out.println("Here are the tasks in your list:");

            for (Task element : tasksArr) {
                listNo++;
                if (element == null) {
                    System.out.println();
                    break;
                } else
                    System.out.println(listNo + "." + element.toString());
            }

            inputCommand(tasksArr, count);
        }

        else if (command.startsWith("mark")) {
            // -5 index for "mark "
            int taskNo = Integer.parseInt(command.substring(5)) - 1;
            tasksArr[taskNo].markAsDone(true);

            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                    + tasksArr[taskNo].toString() + System.lineSeparator());

            inputCommand(tasksArr, count);
        }

        else if (command.startsWith("unmark")) {
            // -7 index for "unmark "
            int taskNo = Integer.parseInt(command.substring(7)) - 1;
            tasksArr[taskNo].markAsDone(false);

            System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                    + tasksArr[taskNo].toString() + System.lineSeparator());

            inputCommand(tasksArr, count);
        }

        else if (command.startsWith("todo")) {
            String taskName = command.substring(5);

            addTask(new Todo(taskName));

            inputCommand(tasksArr, count);
        }

        else if (command.startsWith("deadline")) {
            int split = command.indexOf('/');
            String taskName = command.substring(9,split - 1);
            String byWhen = command.substring(split + 4);

            addTask(new Deadline(taskName,byWhen));

            inputCommand(tasksArr, count);
        }

        else if (command.startsWith("event")) {
            int split = command.indexOf('/');
            String taskName = command.substring(6,split - 1);
            String byWhen = command.substring(split + 4);

            addTask(new Event(taskName,byWhen));

            inputCommand(tasksArr, count);
        }
        else {
            inputCommand(tasksArr, count);
        }
    }

    public static void addTask(Task t){
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("Got it. I've added this task:" + System.lineSeparator() +
                t.toString() + System.lineSeparator() + "Now you have " + taskCount + " task(s) in the list." +
                System.lineSeparator());
    }
}