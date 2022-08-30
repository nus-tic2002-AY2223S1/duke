import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
                
        // System.out.println("Hello from\n" + logo);

        // greetings
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t-----------------------------------------------------------------");

        // user input and echo
        while (true) {
            // user input from console
            String line;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            // if input is "bye", prints goodbye message and terminate
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------------------------");
                break;
            }

            // echo
            System.out.println("\t-----------------------------------------------------------------");
            System.out.println("\t " + line);
            System.out.println("\t-----------------------------------------------------------------");
        }
    }
}
