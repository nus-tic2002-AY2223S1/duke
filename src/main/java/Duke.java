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

        inputCommand(tasksArray, 0);


    }
    public static void inputCommand(String[] tasks, int count) {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if (command.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else if (command.equalsIgnoreCase("list")) {
            int listNo = 0;

            for (String element : tasks) {
                listNo++;
                if (element == null) {
                    System.out.println();
                    break;
                } else
                    System.out.println(listNo + ". " + element);
            }

            inputCommand(tasks, count);
        }
        else {
            tasks[count] = command;
            System.out.println("added: " + command + System.lineSeparator());
            inputCommand(tasks,count + 1);
        }
    }
}
