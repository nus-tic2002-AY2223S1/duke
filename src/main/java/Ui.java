import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String logo = " _______ ______ ___  ___ ______\n"
            + "|_______|  __  |   \\/   |  __  |\n"
            + "   | |  | |  | |  |\\/|  | |  | |\n"
            + "   | |  | |__| |  |  |  | |__| |\n"
            + "   |_|  |______|__|  |__|______|\n";
    private static String greet = "Hello! Tomo here.\nWhat's up? ^_^";
    private static Scanner in;

    public Ui() {

    }

    public static void welcome() {
        System.out.println(logo);
        printBox(greet);
    }


    public static String userInput() {
        in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public static void printBox(String words) {
        System.out.println("_____________________________________________");
        System.out.println(words);
        System.out.println("_____________________________________________");
    }

    public static void printArray(ArrayList<Task> list, int length) {
        String toPrint = "Checklist:";
        for (int i = 0; i < length; i++) {
            int n = i+1;
            toPrint = toPrint + "\n" + n + ") ";
            toPrint = toPrint + list.get(i).getTask();
        }
        printBox(toPrint);
    }
}
