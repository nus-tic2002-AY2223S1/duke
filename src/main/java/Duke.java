import java.util.Scanner;

public class Duke {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        greeting();

        Scanner in = new Scanner(System.in);
        Boolean exit = false;
        while(!exit){
        String line = in.nextLine();
        switch (line){
            case"bye":
                exit = true;
                break;
            default:
                printLine(line);

        }

        }
        in.close();
        goodbye();
    }

    public static void greeting() {
        String logo = "    =    |\\    | |     | ======   |=====\n"
                    + "   / \\   | \\   |  |   |  ||       |     =\n"
                    + "  /___\\  |  \\  |   | |   ||====   |=====\n"
                    + " /-----\\ |   \\ |    |    ||       |   \\\n"
                    + "/       \\|    \\|    |    ======   |    \\\n";
        System.out.println( logo);
        printIndent(LONG_LINE);
        printIndent("Hello! I am Anyer");
        printIndent("What can I do for you?");
        printIndent(LONG_LINE);
    }
    //add"good bye "
    public static void goodbye(){
        printLine("Bye.Hope to see you again soon!");
    }

    protected static void printLine(String line){
        printIndent(LONG_LINE);
        printIndent(line);
        printIndent(LONG_LINE);
    }
    protected static void printIndent(String line){
        System.out.println("   "+line);
    }
}
