package Duke;
import java.util.Scanner;

import Duke.Exception.DukeException;
import Duke.TaskList.Task;

public class Duke {
    public static String line = "\n----------------------------------\n";
    static public String userInput = "";
    static public Task taskList[] = new Task[100];
    static public int taskListCount = 0;

    public static void bye() {
        System.out.println(line + "Bye. Hope to see you again soon!" + line);
    }

    public static void main(String[] args) throws DukeException {
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

        Scanner scanner = new Scanner(System.in);
        System.out.println(line + "Hello! I'm Pepe\nWhat can I do for you?" + line);

        userInput = scanner.nextLine();
        while(!userInput.equalsIgnoreCase("bye")) {
            if (userInput.startsWith("list")) {
                Task.list();
            }
            else if(userInput.contains("mark")) {
                Task.markTask();
            }
            else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                Task.addTask(userInput);
                System.out.println(line + "Got it. I've added this task:\n");
                System.out.println(taskList[taskListCount-1].toString());
                System.out.println("Now you have " + taskListCount + " tasks in the list." + line);
            }
            else if (userInput.startsWith("delete")) {
                Task.deleteTask();
            }
            else {
                try {
                    throw new DukeException();
                } catch (DukeException e) {
                    System.out.println(line + "\uD83D\uDE1F OOPS!!! I'm sorry, but I don't know what that means" + line);
                }
            }

            userInput = scanner.nextLine();
        }
        bye();
    }
}
