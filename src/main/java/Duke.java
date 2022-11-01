import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    protected static String[] Tasks = new String[100];
    private static int countTask = 0;

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

                Parser.identifyCommand(userInput);
            } while (!userInput.equals("bye"));
            exit();
        }

        public static void exit() {
            System.out.println("Bye Siying!" + System.lineSeparator() + "Enjoy your day ;)");
        }

    }

    public static class Parser {

        public static void identifyCommand(String input) {
            switch (input){
                case "list":
                    for (int i = 0; i < countTask; i++) {
                        System.out.println(i+1 + ". " + Tasks[i]);
                    }
                    break;
                case "bye":
                    break;
                default:
                    Tasks[countTask] = input;
                    countTask++;
                    System.out.println("added: " + input);
                    break;
            }

        }

    }

    public static void main(String[] args) {
        Ui.greet();
        Ui.readInput();

    }
}


