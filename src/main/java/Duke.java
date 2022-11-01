import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static class Ui {

        private static final String DIVIDER = "____________________________________________________________";
        private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

        public static void greet() {
            System.out.println("Hello from\n" + LOGO);
            System.out.println("Hello Siying!\n" + "How's your day today?\n" + DIVIDER + System.lineSeparator() + "Below are your tasks due today\n" + DIVIDER );
            System.out.println("What would you like to do now?");
        }

        public static void readInput(){
            String userInput;
            Scanner in;

            do {
                in = new Scanner(System.in);
                userInput = in.nextLine();
                System.out.println(userInput);
            } while (!userInput.equals("bye"));
            exit();
        }

        public static void exit() {
            System.out.println("Bye Siying!" + System.lineSeparator() + "Enjoy your day ;)");
        }

    }

    public static void main(String[] args) {
        Ui.greet();
        Ui.readInput();

    }
}


