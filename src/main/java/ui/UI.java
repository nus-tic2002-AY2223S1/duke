package ui;

import java.util.Scanner;

import static ui.ErrorMessages.TASK_NUMBER_OOB;
import static ui.TaskMessages.*;

public class UI {

    // print task
    public static void printTask(String task){
        System.out.println(task.toString());
    }

    // print line for formatting
    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    // print intro logo
    public static void printIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello there! I'm Duke\nWhat can I do for you?");
        printLine();
    }

    // to simplify printing of error
    public static void printError(String message) {
        System.out.println(message);
    }

    // to simplify printing of messages
    public static void printMessage(String message) {
        System.out.println(message);
    }

    // to simplify printing general errors
    public static void printStandardError() {
        System.out.println("Sorry, I don't understand what you mean!");
    }

    // to retrieve and store user command
    public static String getUserCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Task Action: ");
        String fullInputLine = in.nextLine();
        System.out.println("[You have entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    // print bye message
    public static void printBye(){
        System.out.println(MESSAGE_EXIT);
    }

    public static void printArrayOOB(){
        UI.printLine();
        UI.printError(TASK_NUMBER_OOB);
        UI.printLine();
    }


}
