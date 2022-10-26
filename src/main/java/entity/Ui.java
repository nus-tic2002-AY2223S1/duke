package entity;

import exceptions.DukeException;

import java.util.List;

public class Ui {
    public static String breakLine =  "-------------------------------------------------";
    public static String tab = "     ";
    public static void dukeInit() {
        String logo = "          ______            _  _______    ______            _        _______ \n" +
                "|\\     /|(  ___ \\ |\\     /|( )(  ____ \\  (  __  \\ |\\     /|| \\    /\\(  ____ \\\n" +
                "( \\   / )| (   ) )| )   ( ||/ | (    \\/  | (  \\  )| )   ( ||  \\  / /| (    \\/\n" +
                " \\ (_) / | (__/ / | | _ | |   | (_____   | |   ) || |   | ||  (_/ / | (__    \n" +
                "  ) _ (  |  __ (  | |( )| |   (_____  )  | |   | || |   | ||   _ (  |  __)   \n" +
                " / ( ) \\ | (  \\ \\ | || || |         ) |  | |   ) || |   | ||  ( \\ \\ | (      \n" +
                "( /   \\ )| )___) )| () () |   /\\____) |  | (__/  )| (___) ||  /  \\ \\| (____/\\\n" +
                "|/     \\||/ \\___/ (_______)   \\_______)  (______/ (_______)|_/    \\/(_______/\n" +
                "                                                                             ";
        System.out.println("Hello from bowen's\n" + logo + Ui.breakLine);
        System.out.println(Ui.tab + Ui.breakLine);
        System.out.println(Ui.tab + "Hello! I'm Duke\n" + Ui.tab + "What can I do for you?");
        System.out.println(Ui.tab + Ui.breakLine);
    }

    public static void echoText(String text) {
        System.out.println(Ui.tab + Ui.breakLine);
        System.out.println(Ui.tab + text);
        System.out.println(Ui.tab + Ui.breakLine);
    }

    public static void printList(List<Task> tasks) {
        System.out.println(Ui.tab + Ui.breakLine);
        int counter = 0;
        for (Task task : tasks)
            System.out.println(Ui.tab + ++counter + ". " + task);
        System.out.println(Ui.tab + Ui.breakLine);
    }

    public static void showErrMessage(DukeException err) {
        echoText(err.getMessage());
    }
}
