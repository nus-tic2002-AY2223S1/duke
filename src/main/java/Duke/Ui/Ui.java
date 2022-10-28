package Duke.Ui;
import Duke.Exception.DukeException;
import Duke.Parser.*;
import Duke.TaskList.*;

import static Duke.Duke.*;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public Scanner userInput;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    public static void line() {
        System.out.println("\n_______________________________________________________________________________________________\n");
    }

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
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public static void addedTask() {
        line();
        System.out.println("Got it. I've added this task:\n");
        System.out.println(taskList[taskListCount-1].toString());
        System.out.println("Now you have " + taskListCount + " tasks in the list.");
        line();
    }

    public static void input(String userInput) throws DukeException {
        Parser command = new Parser(userInput);
        try {
            switch(command.getCommand()) {
                case "list":
                    Task.list();
                    break;
                case "todo":
                    ToDo.addTask(userInput);
                case "done":
                    Task.markTask();
                    break;
                case "undone":
                    Task.markTask();
                    break;
                case "bye":
                    bye();
                    break;
            }
        }
        catch (Exception e){
            throw new DukeException();
        }
    }
}
