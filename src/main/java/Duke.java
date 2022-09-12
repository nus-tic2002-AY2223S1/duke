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

        inputCommand();
    }
    public static void inputCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if (command.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else {
            System.out.println(command + System.lineSeparator());
            inputCommand();
        }
    }
}
