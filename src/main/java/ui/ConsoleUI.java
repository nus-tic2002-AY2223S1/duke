package ui;

import java.util.Scanner;

public class ConsoleUI implements UI {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        displayLineBreak();
        System.out.println("Greeting from Duke! ;>");
        System.out.println("How may I assist you today :)");
        displayLineBreak();
    }

    @Override
    public void displayLineBreak() {
        System.out.println("+-----------------------------------------+");
    }

    public String getInput() {
        System.out.print("~@duke >>> ");
        return scanner.nextLine();
    }
}
