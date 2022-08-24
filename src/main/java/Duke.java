import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner input = new Scanner(System.in);
        System.out.print("Hello! I'm Duke\n"+"What can I do for you?:\n");
        while (true) {
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(line);
        }
    }
}

