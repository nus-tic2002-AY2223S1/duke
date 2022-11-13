package Duke.Ui;
import Duke.Exception.DukeException;
import Duke.Parser.*;
import Duke.Storage.Storage;
import Duke.TaskList.*;

import static Duke.Duke.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static Scanner userInput;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    public static Scanner getUserInput() {
        return userInput;
    }

    public static void line() {
        System.out.println("\n_______________________________________________________________________________________________\n");
    }

    /**
     * hello method is to output the initial message the user sees upon interaction with the program
     */
    public static void hello() {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠔⠋⠀⠐⠈⠉⠉⠉⠀⠀⠀⠉⠓⢄⠀⣀⠄⠚⠙⠉⠁⠀⠉⠉⠉⠁⠂⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠐⣜⣦⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⡁⠀⠀⠀⠀⠀⠀⣀⣀⣤⡤⠤⣤⣄⣀⣀⠀⠘⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣷⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣰⣥⠞⠀⠀⠀⠀⢠⡴⠟⠋⠉⠀⠀⠀⠀⠀⠉⠙⠻⢶⣼⣷⣠⣤⡴⠶⠶⠶⠶⠶⢦⣤⣤⣈⠙⣷⣤⣀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣴⢳⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣀⣈⣻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣈⠀⠀⠉⠈⠑⢄⠀⠀\n" +
                "⠀⠀⠀⠀⢀⣼⣿⡋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠶⢛⣫⣭⣭⠿⠾⠯⣭⣭⣍⣛⢦⣀⣤⣶⣞⣋⣭⢭⣭⣭⣭⣭⣟⣓⣶⠀⠑⠄\n" +
                "⠀⢀⣠⠴⡩⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⡤⠴⢞⣉⡴⠚⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⠻⡟⠛⠉⠉⠉⠀⠀⠀⠀⠀⠀⠈⠉⠉⠓⠇⠈\n" +
                "⢤⣿⣼⠂⠀⠀⣿⠀⠀⠀⠀⠀⠀⣴⡛⠛⢛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢂\n" +
                "⣿⣿⠙⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠈⠉⠛⠛⢷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠘\n" +
                "⢹⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢤⡿⡿⠲⢤⣄⣀⣀⣀⣀⣀⣤⣴⠶⠞⢋⡿⠲⠶⠦⢤⣶⢦⣴⣶⡶⠶⠶⠞⠛⠋⣁⡀\n" +
                "⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⣽⠟⠛⠲⠿⠭⠭⠭⠭⠥⠤⣴⠞⠛⠋⠀⠀⠀⠀⠀⣿⢸⡇⠀⠀⠀⢀⡤⢠⡾⠟⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿⠀⠀⠀⠀⠀⠀⣀⣤⡶⢟⣫⣤⣶⣶⣶⣶⣦⣤⣤⣸⣧⢿⣤⡴⠎⠻⡗⠉⠀⠀⠀\n" +
                "⠘⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣄⠀⠀⠀⠀⢀⡇⣿⠀⠀⠀⢀⣾⣿⣿⣷⡾⠿⠟⠋⣉⣉⣉⠉⢻⣷⣝⠋⢹⡼⡇⠀⠀⠀⠙⢆⠀⠀⠀\n" +
                "⠀⠀⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣀⠸⢇⣟⣤⣶⣿⠿⢟⣋⣩⣤⣶⣶⣾⡿⠟⠛⠻⢿⣦⣽⣿⣷⣾⣇⣿⡀⠀⠀⠀⠨⢆⠀⠀\n" +
                "⠀⠀⠀⠸⣿⣭⣭⣶⣶⣶⣶⣶⣼⣿⣿⣿⣿⣿⡟⣛⣭⣴⣾⠿⠟⠛⠉⠁⠀⠀⠀⢀⣀⣀⣀⡉⠙⠛⠻⣿⣿⣿⡿⢿⣷⣶⣦⡾⣆⠀\n" +
                "⠀⠀⠀⠀⠙⠋⠉⣿⡇⣿⣿⣿⣿⣿⣟⣋⣁⣥⣾⡿⠛⠛⠻⠶⠶⠶⣤⣤⣄⣀⣀⣸⡿⠿⣿⡿⢿⠿⣷⣦⣉⠻⣿⣦⡴⠿⠻⣧⣾⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣿⡇⠙⣻⣿⣿⣿⣿⣿⣿⠛⠛⠿⣷⣶⣤⣤⣤⣀⣀⣀⣀⠈⠉⢹⣿⡙⣿⡄⠸⠀⢀⠉⠻⣷⣼⣿⣀⣠⣴⣿⢹⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢻⣇⢰⣿⣏⠙⠻⣭⣿⣿⣿⣿⣿⣿⣷⣾⣯⣭⣛⠿⣿⣙⠛⠛⠛⠻⣿⣾⣿⣷⣶⣶⣤⣀⠀⠙⢿⣯⣉⣾⡟⢸⠀\n" +
                "⠂⠀⠀⠀⠀⢀⣾⣾⣿⣿⣿⣿⣿⣶⣶⣶⣶⣶⣶⣾⣿⣿⣿⣿⣿⣿⣷⣜⢿⣿⣿⣿⣶⣾⣿⣿⣿⣿⣾⣿⣿⣿⣷⣦⣙⠿⣿⣰⡇⠀\n" +
                "⣤⣶⣶⣶⣤⣼⣿⡏⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣭⣭⣉⣉⣉⠉⠙⠛⢿⣧⡘⢿⣿⡿⠿⠿⠿⠿⠿⠿⠿⢻⡿⠿⢿⣿⣦⡈⢻⡆⠀\n" +
                "⠉⠉⠉⠉⠉⠙⠛⠁⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠙⠻⠿⠿⣷⣄⠀⠀⠹⣿⣦⠻⣷⣄⡀⠀⡠⣔⡴⠖⠉⠀⠀⠀⠀⠈⣿⠘⣷⠀\n" +
                "⢀⣴⣿⡿⢠⣶⣷⣦⠀⣼⣷⣶⡄⣾⠀⣷⠀⢸⡇⢸⣷⡄⢠⣿⡎⣿⣧⣴⣶⣾⣿⣷⣜⠿⣿⡗⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⢹⡀⣿⠀\n" +
                "⢸⣏⠀⠀⣿⡇⠀⣹⡇⣿⣷⡿⠃⣿⠀⣿⡀⣸⡇⢸⡿⣷⣼⢻⡇⢿⣿⠿⠿⠛⠛⠛⢿⣦⡈⠻⣯⣱⠀⠀⠀⠀⠀⠀⠀⢀⡾⢰⡏⠀\n" +
                "⠈⠻⠿⠗⠘⠿⠿⠟⠀⠿⠃⠀⠀⠿⠀⠛⠿⠿⠃⠸⠇⠻⠇⠸⠇⢸⣿⡄⠀⠀⠀⠀⠀⠻⣷⣦⣈⡛⡗⠒⠶⣖⣛⣛⣭⣽⡾⠟⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠈⢙⣻⣿⠿⡿⠛⠛⠛⠉⠉⠀⠀⠀⠀⠀";

        System.out.println(logo);
        line();
        System.out.println("Hello! I'm Pepe\nWhat can I do for you?");
        line();
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * addedTask method is to print out the success of adding a task
     * @param tl is an arraylist
     */
    public static void addedTask(ArrayList<Task> tl) {
        System.out.println("Got it. I've added this task:\n");
        System.out.println(tl.get(tl.size()-1).toString());
        System.out.println("Now you have " + tl.size() + " tasks in the list.");
    }

    /**
     * input method is to take in the user input and finding out which command did the user enter
     * @param task is a TaskList to be displayed
     * @param userInput is the input the user has entered
     * @throws DukeException
     */
    public static void input (Tasklist task, String userInput) throws DukeException {
        Parser process = new Parser(userInput);
        try {
            switch (process.getCommand()) {
                case "list":
                    Tasklist.display(Tasklist.getList(), "list");
                    break;
                case "find":
                    Tasklist.display(Tasklist.findTask(process.getTaskDesc()), "find");
                    break;
                case "mark":
                    Tasklist.markTask(userInput, true);
                    break;
                case "unmark":
                    Tasklist.markTask(userInput, false);
                    break;
                case "delete":
                    Tasklist.deleteTask(process.getListIdx());
                    break;
                case "todo":
                    Tasklist.addTodo(new ToDo(process.getTodoDesc(), false));
                    break;
                case "deadline":
                    Tasklist.addDeadline(new Deadline(process.getDeadlineDesc(), process.getDatelineDate(), false));
                    break;
                case "event":
                    Tasklist.addEvent(new Event(process.getEventDesc(), process.getEventDate(), process.getEventStart(), process.getEventEnd(), false));
                    break;
                case "bye":
                    bye();
                    break;
            }
            if(!process.getCommand().equals("find")) {
                Storage.save(Storage.getFile().getAbsolutePath(), task);
            }
        }
        catch(IOException e) {
            throw new DukeException("Sorry,unable to save file.");
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Sorry. I do not understand.");
        }
    }

    /**
     * @param error is the error message to be printed out
     */
    public static void error(String error) {
        System.out.println(error);
    }
}
