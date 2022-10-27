package nus.duke.ui;

import nus.duke.data.DukeException;
import nus.duke.tasklist.TaskList;
import nus.duke.tasklist.Task;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    /** Divider after system messages. */
    private static final String DIVIDER= "____________________________________________________________";
    /** Pre-set offset for printing. */
    private static final String LINE_PREFIX = "     ";
    private final Scanner in;
    private final PrintStream out;
    /**
     * Constructor for Ui.
     */
    public Ui(InputStream in, PrintStream out){
        this.in = new Scanner(in);
        this.out = out;
    }
    /**
     * Default constructor for Ui.
     */
    public Ui(){
        this(System.in, System.out);
    }
    /**
     * Text UI to prompt user to enter command.
     */
    public String readCommand(){
        out.println(LINE_PREFIX + "Enter command: ");
        String command = in.nextLine();
        return command;
    }
    /**
     * Text UI for displaying DukeException errors.
     */
    public void showError(String error){
        out.print(LINE_PREFIX + error);
    }
    /**
     * Text UI for duke welcome message.
     */
    public void showWelcome(){
        out.print(Messages.MESSAGE_WELCOME);
        showLine();
    }
    /**
     * Text UI to print line divider.
     */
    public void showLine(){
        out.println(DIVIDER);
    }

    public void showByeMessage(){
        out.print(Messages.MESSAGE_GOODBYE);
    }
    public void showLoadingError(DukeException e) {
    }
    /**
     *Customised print for duke.
     */
    public static void print(String toPrint){
        System.out.print(LINE_PREFIX + toPrint);
    }
    /**
     *Getter for the LINE_PREFIX.
     */
    public static String getLinePrefix(){
        return LINE_PREFIX;
    }
    /**
     * Text UI to echo every task from the .txt file.
     */
    public static void echoTasksLoadingFromFile (Task added){
        Ui.print(added.toString() + " added\n");
    }
    /**
     * Text UI to echo when adding or deleting tasks.
     */
    public static void echo(String toEcho, String mode) {
        print("Got it. I've " + mode + " this task:\n");
        print("  " + toEcho + "\n");
        print("Now you have " + TaskList.getListCount() + " tasks in the list.\n");
    }
}
