import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
       
        try (Scanner in = new Scanner(System.in)) {
        System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");
        String input = in.nextLine();
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");  
            } else {
            System.out.println(input);
            }
        }
    }
}
