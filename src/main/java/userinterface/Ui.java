package userinterface;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private String logo = " _______ ______ ___  ___ ______\n"
            + "|_______|  __  |   \\/   |  __  |\n"
            + "   | |  | |  | |  |\\/|  | |  | |\n"
            + "   | |  | |__| |  |  |  | |__| |\n"
            + "   |_|  |______|__|  |__|______|\n";
    private String greet = "Hello! Tomo here.\nWhat's up? ^_^";
    private Scanner in;

    public void welcome() {
        printBox(logo + "\n" + greet);
    }

    public void goodbye() {
        printBox("See you ^_^");
    }

    public String readCommand() {
        in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public void printBox(String words) {
        System.out.println("_____________________________________________");
        System.out.println(words);
        System.out.println("_____________________________________________");
    }

    public void printArray(ArrayList<Task> list, int length) {
        String toPrint = "Checklist:";
        for (int i = 0; i < length; i++) {
            int n = i+1;
            toPrint = toPrint + "\n" + n + ") ";
            toPrint = toPrint + list.get(i).getTask();
        }
        printBox(toPrint);
    }
}
