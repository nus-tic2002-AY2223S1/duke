package Duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String LS = System.lineSeparator();
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void showWelcomeMessage() {
        printMessage(Messages.LOGO, Messages.MESSAGE_WELCOME);
    }

    public void printMessage(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS));
        }
    }

    public void showResultToUser(String output) {
        System.out.println(output);
    }

}
