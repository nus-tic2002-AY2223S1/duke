import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "__           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |__ | |_| |   <  __/\n"
                + "|____| \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println(logo);
        System.out.println("Hello! I'm Luke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while(true){
            String input;

            Scanner in = new Scanner(System.in);
            input = in.nextLine();

            System.out.println(line);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(input);
            System.out.println(line);
        }

    }
}
