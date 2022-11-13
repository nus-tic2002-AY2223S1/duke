package ui;

import java.util.Scanner;

import static ui.ErrorMessages.TASK_NUMBER_OOB;
import static ui.TaskMessages.*;

/**
 * Text UI of the application.
 */
public class UI {

    /** print tasks.*/
    public static void printTask(String task){
        System.out.println(task.toString());
    }

    /** A decorative prefix added to the beginning of lines printed by Duke  */
    public static void printLine(){
        System.out.println("____________________________________________________________");
    }
    /** Intro Logo  */
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
    /**  to simplify printing of error */
    public static void printError(String message) {
        System.out.println(message);
    }

    /**  to simplify printing of messages */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**  to simplify printing of standard error */
    public static void printStandardError() {
        System.out.println("Sorry, I don't understand what you mean!");
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public static String getUserCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Task Action: ");
        String fullInputLine = in.nextLine();
        System.out.println("[You have entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    // print bye message
    public static void printBye(){
        printLine();
        printMessage(MESSAGE_EXIT);
        printLine();
    }

    public static void printArrayOOB(){
        UI.printLine();
        UI.printError(TASK_NUMBER_OOB);
        UI.printLine();
    }


}
