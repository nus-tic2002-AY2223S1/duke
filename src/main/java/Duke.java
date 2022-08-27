import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Duke {
    public static String breaker = "\t____________________________________________________________\n";
    public static Scanner scanner;
    public static String input;
    public static ArrayList<Item> itemList;

    public static void printWithIndentation(String format, String content) {
        System.out.printf(format, breaker, content, breaker);

    }

    public static void getUserInput() {
        input = scanner.nextLine();
    }

    public static void printList() {
        String printStr = "";
        for (int i = 0; i < itemList.size(); i++) {
            printStr += String.format("\t %d. %s\n", i + 1, itemList.get(i).name);
        }

        printWithIndentation("%s%s%s", printStr);
    }

    public static void main(String[] args) {

        //greetings
        printWithIndentation("%s%s%s", "\t Hello! I'm Duke\n \t What can I do for you?\n");

        //init
        scanner = new Scanner(System.in);
        itemList = new ArrayList<>();

        //start
        getUserInput();

        //cases
        while (!input.equalsIgnoreCase("bye")) {

            switch (input.toLowerCase()) {
                case "list":
                    printList();
                    break;
                default:
                    itemList.add(new Item(input));
                    printWithIndentation("%s\t added: %s\n%s", input);
                    break;
            }
            getUserInput();
        }

        //bye
        printWithIndentation("%s\t %s\n%s", "Bye. Hope to see you again soon!");
    }
}
