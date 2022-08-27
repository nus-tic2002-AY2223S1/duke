import java.util.Scanner;
import java.util.Stack;

public class Duke {
    public static String breaker = "\t____________________________________________________________\n";
    public static Scanner scanner;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greetings = breaker
                + "\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n"
                +breaker;

        System.out.println(greetings);

        scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("bye")){
            System.out.printf("%s\t %s\n%s", breaker, input, breaker);
            input = scanner.nextLine();
        }

        System.out.printf("%s\t %s\n%s", breaker, "Bye. Hope to see you again soon!", breaker);
    }
}
