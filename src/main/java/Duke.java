import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static Todo todo = new Todo();
    public static void main(String[] args) {
        intro();
    }

    private static void intro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
        conversation();
    }

    private static void conversation(){
        String inp = "";
        Scanner scanner = new Scanner(System.in);

        while(!isClosing(inp = scanner.nextLine())) {
            if(isOpening(inp)){
                System.out.println("\tHello again!\n");
            } else if (isGet(inp)) {
                printList();
            } else {
                todo.addItem(inp);
                System.out.format("\tadded: %s\n", inp);
            }
        }
        System.out.println("\tBye. Hope to see you again soon!!");
        System.exit(1);
    }

    private static boolean isOpening(String text){
        String[] opening = {"Hello","Hi"};
        return Arrays.stream(opening).anyMatch(text::equalsIgnoreCase);
    }

    private static boolean isClosing(String text){
        String[] closing = {"Bye","See ya"};
        return Arrays.stream(closing).anyMatch(text::equalsIgnoreCase);
    }

    private static boolean isGet(String text){
        String[] closing = {"list","show"};
        return Arrays.stream(closing).anyMatch(text::equalsIgnoreCase);
    }

    private static void printList(){
        ArrayList<Item> items = todo.getItems();
        for(int i = 0; i < items.size(); i++){
            System.out.format("\t%d. %s\n", i+1, items.get(i).getName());
        }
    }
}
