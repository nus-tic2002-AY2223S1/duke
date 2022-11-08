package formatting;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Helper {
    //helper class for formatting

    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern ("d/MM/yyyy HHmm");
    public static String DUKEFILEPATH = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "dukeFile";

    public static void newline(){
        System.out.println("\n");
    }
    public static void separator() {
        System.out.println("____________________________________");
    }

    public static String userInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
