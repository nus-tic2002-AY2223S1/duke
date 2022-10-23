package nus.duke.frontend;

import java.util.Scanner;

public class Ui {

    public static void printCommandMenu(){
        showLine();
        System.out.println("**COMMAND MENU**");
        System.out.println("MARK | UNMARK | DELETE | VIEW | EXIT");
        System.out.println("TODO <<task>> | DEADLINE <<task>> /by <<date>>| EVENT <<TASK>> /at <<date>>");
        System.out.println("Please ensure commands are all capitalised.");
        System.out.println("**END OF COMMAND MENU**");
        // System.out.println("Automatic Testing: /bin/bash runtest.sh");
        showLine();
    }
    public static void awakeDobby(){
        System.out.println("My name is Dobby and I am a free elf!");
        printCommandMenu();
    }

    public static void exit(){
        System.out.print("Goodbye, my friend. Dobby must now hurry to Harry Potter.\n");
    }

    public static String getUserInput(Scanner s){
        String input = s.nextLine();
        return input;
    }

    public static void showLine (){
        System.out.println("_____________________________________________________");
    }

    public static void showHarddiskCreationMessage(){
        System.out.println("Hard disk file created. You can now save all your tasks and it will be loaded the next time.");
    }
}
