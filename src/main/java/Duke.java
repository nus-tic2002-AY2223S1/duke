import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String breakLine = "-------------------------------------------------";
        String tab = "     ";
        System.out.println("Hello from bowen's\n" + logo + breakLine);
        System.out.println(tab + breakLine);
        System.out.println(tab + "Hello! I'm Duke\n" + tab +"What can I do for you?");
        System.out.println(tab + breakLine);

        List<String> tasks = new ArrayList<>();
        String text;
        whileLoop: while (true) {
            text = inputText();
            switch (text) {
                case "bye":
                    echoText("Bye. Hope to see you again soon!");
                    break whileLoop;
                case "list":
                    printList(tasks);
                    break;
                default:
                    tasks.add(text);
                    echoText("added: " + text);
            }
        }

    }
    public static String inputText() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static void echoText(String text){
        String breakLine = "-------------------------------------------------";
        String tab = "     ";
        System.out.println(tab + breakLine);
        System.out.println(tab + text);
        System.out.println(tab + breakLine);
    }
    public static void printList(List<String> tasks) {
        String breakLine = "-------------------------------------------------";
        String tab = "     ";
        System.out.println(tab + breakLine);
        int counter = 0;
        for (String task : tasks)
            System.out.println(tab + ++counter + ". " + task);
        System.out.println(tab + breakLine);

    }
}
