import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke!\n" + "What can I do for you?");
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else {
                System.out.println(line);
            }
        }
    }
}


