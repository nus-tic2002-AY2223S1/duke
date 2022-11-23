package ui;

import storage.Storage;

import java.util.*;

public class Ui {
    /** Messages for UI */
    public static final String UI_DIVIDER = "____________________________________________________________";
    public static final String UI_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Reply Messages */
    public static final String REPLY_LIST = "Here are the tasks in your list: ";
    public static final String REPLY_ADD_TASK = "Got it. I've added this task: ";
    public static final String REPLY_REMOVE_TASK = "Noted. I've removed this task: ";
    public static final String REPLY_MARK_DONE = "Nice! I've marked this task as done: ";
    public static final String REPLY_UNMARK_DONE = "OK, I've marked this task as not done yet: ";
    public static final String REPLY_FIND = "Here are the matching tasks in your list: ";
    public static final String REPLY_DUE = "Here are the tasks in your list matching the due date: ";
    public static final String REPLY_POSTPONE = "OK, I've postponed this task to new date: ";
    public static final String REPLY_LOADED_TASK = "Give me a sec while I load the file... I've added below task(s): ";

    /** Error Messages */
    public static final String ERROR_START = "Ooops, I can't seem to load the system, please try again :(";
    public static final String ERROR_INVALID_COMMAND = "Ooops, I didn't get your command. Please re-enter a valid command.";
    public static final String ERROR_NOMATCHES = "I couldn't find any tasks matching your criteria.";
    public static final String ERROR_INDEXOUTOFBOUNDS = "You have entered an index that is not within the range.";
    public static final String ERROR_MAXTASKLISTSIZE = "You have exceeded max number of tasks possible for the list, please delete tasks before continuing.";
    public static final String ERROR_EMPTYTASKLIST = "You do not have any tasks currently. Create some tasks to get started!";
    public static final String ERROR_INVALID_EVENT = "You have entered an incorrect format for event command, please enter format in eg. event read book /at YYYY-MM-DDTHH:MM.";
    public static final String ERROR_INVALID_DEADLINE = "You have entered an incorrect format for deadline command, please enter format in eg. deadline read book /by YYYY-MM-DDTHH:MM.";
    public static final String ERROR_INVALID_POSTPONE = "You have entered an incorrect format for postpone command, please enter format in eg. postpone read book /to YYYY-MM-DDTHH:MM.";
    public static final String ERROR_INVALID_POSTPONETYPE = "You are trying to postpone an incorrect task type. Postpone can only be done for deadline & events.";
    public static final String ERROR_INVALID_INDEX = "You have entered an invalid index, please enter index as numeric.";
    public static final String ERROR_INVALID_DATETIME = "You have entered an invalid datetime, please enter format in eg. YYYY-MM-DDTHH:MM.";
    public static final String ERROR_FILENOTFOUND = "Hmmm, I couldn't find the file requested. I will proceed to save under data/duke.txt instead.";
    public static final String ERROR_LOAD = "Oh no file format is incorrect, I couldn't load the remaining tasks, please recheck the file format if required.";

    private static Scanner in;

    /** Welcome message */
    public void showWelcome(){
        System.out.println("Hello from\n" + UI_LOGO + UI_DIVIDER);
        String greeting = showGreetings().get(0);
        String text = showGreetings().get(1);
        System.out.println(greeting + " Siying~!" + System.lineSeparator() + text + System.lineSeparator() + UI_DIVIDER);
    }

    public static ArrayList<String> showGreetings (){
        ArrayList<String> greetings = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12) {
            greetings.add("Good Morning");
            greetings.add("Rise and shine, it's a new day ahead!");
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            greetings.add("Good Afternoon");
            greetings.add("You still have half a day to make the change you want :)");
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            greetings.add("Good Evening");
            greetings.add("The day is almost over, remember to get some me time ~");
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            greetings.add("Good Night");
            greetings.add("You deserve a good rest!");
        } else {
            greetings.add("Hello");
            greetings.add("Life is about appreciating the simple things in life");
        }

        return greetings;
    }

    /** Goodbye message */
    public void showGoodbye(){
        System.out.println("Bye Siying!" + System.lineSeparator() + "Enjoy your day;)");
    }

    /** Prompt user to input */
    public static String readCommand(){
        System.out.println("What would you like to do now?");
        in = new Scanner(System.in);
        String command = in.nextLine();
        return command;
    }
    public static boolean promptFilepath (){
        System.out.println("Do you want to load the default file " + Storage.filePath + "? (Y/N)");
        in = new Scanner(System.in);
        String input = in.nextLine();

        switch (input.trim().toUpperCase()) {
            case "Y":
                return true;
            case "N":
                System.out.println("Got it, what file should I load instead? Enter N again to skip.");
                in = new Scanner(System.in);

                switch (in.nextLine().trim()){
                    case "N":
                        return false;
                    default:
                        Storage.filePath = in.nextLine();
                        return true;
                }
        }
        return false;
    }

    /** Print divider line */
    public void showLine(){
        System.out.println(UI_DIVIDER);
    }

}