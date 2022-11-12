package Duke.UI;

import Duke.Common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents an Ui which will display information for the user
 */
public class Ui {
    private static final String LS = System.lineSeparator();
    private final Scanner in;
    private final PrintStream out;


    public Ui() {
        this(System.in, System.out);
    }

    /**
     *  Constructor of Ui
     *
     * @param in to get command from user
     * @param out to output the result of executed command
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     *  To get user's command
     *
     * @return a String with the user's command
     */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     *  To get user's confirmation
     *
     * @param warning to output the warning message for user to confirm
     * @return a boolean with the user's confirmation
     */
    public boolean getUserConfirmation(String warning) {
        assert (!warning.isEmpty());
        System.out.println(warning);
        System.out.println("(Y/N)");
        return in.nextLine().equalsIgnoreCase("y");
    }

    /**
     *  To print welcome message
     */
    public void showWelcomeMessage() {
        printMessage(Messages.LOGO, Messages.MESSAGE_WELCOME);
    }

    /**
     *  To print multiple String of messages
     *
     * @param message is an array of parameters of type String to be printed out one by one
     */
    public void printMessage(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS));
        }
    }
    /**
     *  To print an output
     *
     * @param output is a String to be printed out
     */
    public void showResultToUser(String output) {
        System.out.println(output);
    }

}
