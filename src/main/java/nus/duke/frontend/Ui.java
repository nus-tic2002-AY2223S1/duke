package nus.duke.frontend;

import java.util.Scanner;

public class Ui {

    public static void printCommandMenu(){
        System.out.println("____________________Command Menu____________________");
        System.out.println("MARK | UNMARK | DELETE | VIEW | EXIT | TODO <<task>> | DEADLINE <<task>> /by <<date>>| EVENT <<TASK>> /at <<date>>");
        // System.out.println("Automatic Testing: /bin/bash runtest.sh");
        System.out.println("_____________________________________________________");
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
        System.out.println("____________________");
    }
}
