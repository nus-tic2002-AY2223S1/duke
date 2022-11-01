package ui;

import taskList.Task;

public class UI {

    public static void printTask(Task task){
        System.out.println(task.toString());
    }

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void printIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello there! I'm Duke\nWhat can I do for you?");
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printStandardError() {
        System.out.println("Sorry, I don't understand what you mean!");
    }

    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon! ");
    }
}
