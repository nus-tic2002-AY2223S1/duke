package nus.duke.frontend;

import java.util.Scanner;
import static nus.duke.frontend.CommonPrintStatements.*;

public class Ui {
    public static String getUserInput(Scanner s){
        String input = s.nextLine();
        return input;
    }

    public static void showLine() {
        System.out.println("_______________ʕ　·ᴥ·ʔ COMMAND MENU  ʕ·ᴥ·　ʔ_______________");
    }

    public static void printCommandMenu() {
        showLine();
        System.out.println("MARK | UNMARK | DELETE | VIEW | EXIT | REMINDERS");
        System.out.println("TODO <<task>> | FILTER <<keyword>>");
        System.out.println("DEADLINE <<task>> /by <<dd/MM/YYYY>>| EVENT <<TASK>> /at <<venue>> /on <<dd/MM/YYY>>");
        System.out.println("Please ensure commands are all capitalised.");
        showLine();
    }

    public static void printDobby() {
        System.out.println(DOBBY_ARTWORK);
    }

    public static void startProgram() {
        printDobby();
        //System.out.println(START_PROGRAM_MESSAGE);
        System.out.println(ASK_FOR_USER_INPUT_MESSAGE);
        printCommandMenu();
    }

    public static void exit(){
        System.out.println(EXIT_PROGRAM_MESSAGE);

    }

    public static void showHardDiskLoadedMessage() {
        System.out.println(HARDDISK_LOADED_MESSAGE);
    }

    public static void showHardDiskLoadingMessage(){
        System.out.println(LOADING_ARTWORK);
    }
    
    public static void showHardDiskCreationMessage() {
        System.out.println("\n");
        System.out.println(HARDDISK_CREATED_MESSAGE);
        System.out.println("");
    }
}
