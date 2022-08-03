import java.util.Objects;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.printf("Hello from %n%s%nWhat can I do for you?%n", logo);
        Scanner myObj = new Scanner(System.in);
        String format = "    ─────────────────────────────────────────\n    %s\n    ─────────────────────────────────────────\n";

        while(true){
            String input = myObj.nextLine();
            if (Objects.equals(input, "")){
                continue;
            }
            else if (Objects.equals(input, "bye")){
                System.out.format(format, "Bye. Hope to see you again soon!");
                break;
            }
            System.out.format(format, input);
        }
    }
}
