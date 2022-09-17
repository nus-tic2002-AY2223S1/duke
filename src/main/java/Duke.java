import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        intro();
    }

    private static void intro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.format("Hello! I'm \n%s\nWhat can I do for you?\n", logo);
        conversation();
    }

    private static void conversation(){
        String inp = "";
        Scanner scanner = new Scanner(System.in);

        while(!isClosing(inp = scanner.next())) {
            if(isOpening(inp)){
                System.out.println("\tHello again!\n");
            } else {
                System.out.format("\t%s\n", inp);
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
}
