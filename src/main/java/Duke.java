import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static Todo todo = new Todo();
    public static void main(String[] args) {
        intro();
    }

    private static void intro(){
        String logo =
                  " _____             _   \n"
                + "/  __ \\_   _ _  __|_| ___\n"
                + "| |   | | | | |/ /| |/ _ \\\n"
                + "| |__ | |_| |  /  | | |_| |\n"
                + "\\____/ \\__,_|_|   |_|\\___/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Curio\nWhat can i do for you?\n");
        conversation();
    }


    private static void conversation(){
        String inp = "";
        Scanner scanner = new Scanner(System.in);

        while(!isClosing(inp = scanner.nextLine())) {
            if(isOpening(inp)){
                System.out.println("\tHello again!\n");
            } else if (isGet(inp)) {
                todo.showList();
            } else if(isUnmark(inp)){
                todo.updateDoneState(Integer.parseInt(inp.replaceAll("[^0-9]", "")), false);
            } else if(isMark(inp)){
                todo.updateDoneState(Integer.parseInt(inp.replaceAll("[^0-9]", "")), true);
            } else if(isTodo(inp)) {
                todo.addItem(inp, "T");
            } else if (isDeadline(inp)) {
                todo.addItem(inp, "D");
            } else if (isEvent(inp)) {
                todo.addItem(inp, "E");
            } else {
                System.out.println("I'm sorry. What do you mean?");
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

    private static boolean isMark(String text){
        String[] closing = {"mark","done"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    private static boolean isUnmark(String text){
        String[] closing = {"unmark","not done"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    private static boolean isTodo(String text){
        String[] closing = {"todo"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    private static boolean isDeadline(String text){
        String[] closing = {"deadline"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }

    private static boolean isEvent(String text){
        String[] closing = {"event"};
        return Arrays.stream(closing).anyMatch(text::contains);
    }
}
