import java.util.Scanner;

public class Ui
{
    //constructor
    Ui()
    {

    }
    protected static void showLoadingError()
    {
        System.out.println("There is no TaskList");
    }

    protected static void showWelcome()
    {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
    }
    protected static void showLine()
    {
        System.out.println("____________________________________________________________________________");
    }

    protected static String readCommand()
    {
        Scanner in = new Scanner(System.in); //readCommand
        return in.nextLine(); //readCommand
    }

    protected static void showError(String Message)
    {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
