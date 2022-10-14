package ui;

import java.util.Scanner;

public class Ui {
    public Scanner input;

    public void showLoadingError() {
        System.out.println("There is no tasks.txt file, so we will create an empty task list for you.");
    }

    public void showWelcome() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
    }

    public String readCommand() {
        Scanner input = new Scanner(System.in);
//        String line = input.nextLine();

        return input.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
