package ui;

import java.util.Scanner;

public class Ui {
    public Scanner input;

    /**
     * This method displays a loading error message when tasks.txt file does not exist.
     * It will also alert user that we will be creating an empty task list for them
     */
    public void showLoadingError() {
        System.out.println("There is no tasks.txt file, so we will create an empty task list for you.");
    }

    /**
     * This method displays a welcome message to user
     */
    public void showWelcome() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Services & command are as follow:\n ");
        System.out.println("        1.  [Add todo task] todo {description}");
        System.out.println("        2.  [Add deadline task] deadline {description} /by {deadline}");
        System.out.println("        3.  [Add event task] event {description} /at {datetime}");
        System.out.println("        4.  [Add doWithinPeriod task] dowithinperiod {description} /between {start date} /and {end date}");
        System.out.println("        5.  [list tasks] list");
        System.out.println("        6.  [Search tasks with keyword] find {description key word}");
        System.out.println("        7.  [Search deadline/event tasks with datetime] findtask {datetime}");
        System.out.println("        8.  [Delete task] delete {task index}");
        System.out.println("        9.  [Mark task done] mark {task index}");
        System.out.println("        10. [Mark task undone] unmark {task index}");
        System.out.println("        11. [Prioritise task] priority {task index} {priority level}");
        System.out.println("        12. [End process] bye\n");
        System.out.println("What can I do for you?");
    }

    /**
     * Allow user to enter command and return user's command
     *
     * @return user's command
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * This method helps to display a straight line to user
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * This method helps to display message to user
     *
     * @param message Message to show user
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * This method helps to display error message to user
     *
     * @param message Error message to show user
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
