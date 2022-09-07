import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String breakLine = "-------------------------------------------------";
        String tab = "     ";
        System.out.println("Hello from\n" + logo + breakLine);
        System.out.println(tab + breakLine);
        System.out.println(tab + "Hello! I'm Duke\n" + tab +"What can I do for you?");
        System.out.println(tab + breakLine);


        String text;
        whileLoop: while (true) {
            text = inputText();
            switch (text) {
                case "bye":
                    echoText("Bye. Hope to see you again soon!");
                    break whileLoop;
                default:
                    echoText(text);
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
}
