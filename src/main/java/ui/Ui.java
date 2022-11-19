package ui;

import entities.FileManager;

import java.util.Scanner;

public class Ui {
    public static void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from YH\n" + logo);
        System.out.println("    ---------------------------------------");
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
    }
    public static void showLine(){
        System.out.println("---------------------------------------");
    }

    public static void showLoadingError(){
        System.out.println("OOPS! Loading error!");
    }

    public static void showMessage(String message){
        System.out.println(message);
    }
    public static void showError(String errorMessage){
        System.out.println(errorMessage);
    }
    public static String readCommand(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}
