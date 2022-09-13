import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");

        String userReply;
        Scanner in = new Scanner(System.in);
        userReply = in.nextLine();

        while (!userReply.equals("bye")) {
            System.out.println(userReply);
            in = new Scanner(System.in);
            userReply = in.nextLine();
        }
            System.out.println("Bye. Hope to see you again soon!");

    }
}
