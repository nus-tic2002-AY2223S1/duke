package ui;

import java.util.Scanner;

import static ui.ErrorMessages.TASK_NUMBER_OOB;
import static ui.TaskMessages.*;

public class UI {

    public static void printTask(String task){
        System.out.println(task.toString());
    }

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

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

    public static void printError(String message) {
        System.out.println(message);
    }
    public static void printMessage(String message) {
        System.out.println(message);
    }
    public static void printStandardError() {
        System.out.println("Sorry, I don't understand what you mean!");
    }

    public static String getUserCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Task Action: ");
        String fullInputLine = in.nextLine();
        System.out.println("[You have entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    public static void printBye(){
        System.out.println(MESSAGE_EXIT);
    }

    public static void printArrayOOB(){
        UI.printLine();
        UI.printError(TASK_NUMBER_OOB);
        UI.printLine();
    }
}
