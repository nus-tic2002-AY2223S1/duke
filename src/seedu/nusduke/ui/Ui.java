package seedu.nusduke.ui;

import seedu.nusduke.data.DukeException;
import seedu.nusduke.tasklist.TaskList;
import seedu.nusduke.tasklist.Task;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Ui {
    private static final String DIVIDER= "____________________________________________________________";
    private static final String LINE_PREFIX = "     ";
    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    public Ui(){
        this(System.in, System.out);
    }

    public String readCommand(){
        out.println(LINE_PREFIX + "Enter command: ");
        String command = in.nextLine();
        return command;
    }

    public void showError(String error){
        out.print(LINE_PREFIX + error);
    }
    public void showWelcome(){
        out.print(Messages.MESSAGE_WELCOME);
        showLine();
    }
    public void showLine(){
        out.println(DIVIDER);
    }

    public void showByeMessage(){
        out.print(Messages.MESSAGE_GOODBYE);
    }
    public void showLoadingError(DukeException e) {
    }
    public static void print(String toPrint){
        System.out.print(LINE_PREFIX + toPrint);
    }
    public static String getLinePrefix(){
        return LINE_PREFIX;
    }
    public static void echoTasksLoadingFromFile (Task added){
        Ui.print(added.toString() + " added\n");
    }

    public static void echo(String toEcho, String mode) {
        print("Got it. I've " + mode + " this task:\n");
        print("  " + toEcho + "\n");
        print("Now you have " + TaskList.getListCount() + " tasks in the list.\n");
    }
}
