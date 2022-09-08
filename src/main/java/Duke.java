import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        clearScreen();
        System.out.println("Wakanda forever! I'm Winston Duke\n");
        System.out.println("What can I do for you?\n");
        readInput();
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void readInput() {
        String command;
        do {
            boolean exist = false;
            Scanner userInput = new Scanner(System.in);
            command = userInput.nextLine();
            if (command.equals("bye")) {
                exit();
            } else {
                echo(command);
            }
        } while (!(command.equals("bye")));
    }
    public static void echo(String toEcho) {
        System.out.print(toEcho + "\n");
    }
    public static void exit() {
        System.out.print("Bye. Remember!\nIn times of crisis, the wise build bridges while the foolish build barriers.");
    }

}
