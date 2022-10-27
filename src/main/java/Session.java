import java.io.IOException;
import java.util.Scanner;

public class Session {
    
    // properties
    private final Repository sessionRepo;


    // constructors
    public Session (Repository repo) {
        this.sessionRepo = repo;
    }

    // methods
    public void start() throws IOException {
        sessionRepo.open();

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("System booting up...\n" + logo);

        // greetings
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println();

        // declaration
        String inputLine;
        String commandLine;
        Scanner input = new Scanner(System.in);

        TaskList sessionTaskList = new TaskList();
        
        // user input and echo
        while (true) {
            inputLine = input.nextLine();
            // instantiate an array to tokenize the input
            String[] lineArray = inputLine.split(" ");
            // obtain the command, first element
            commandLine = lineArray[0];

            // if input is "bye", prints goodbye message and terminate
            if (inputLine.equalsIgnoreCase("bye")) {
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------------------------");
                break;
            } 
            else if (commandLine.equalsIgnoreCase("delete")) {
                sessionTaskList.deleteTask(lineArray);
            }
            else if (commandLine.equalsIgnoreCase("mark")) {
                sessionTaskList.markTask(lineArray);
            }
            else if (commandLine.equalsIgnoreCase("unmark")) {
                sessionTaskList.unmarkTask(lineArray);
            }
            else if (commandLine.equalsIgnoreCase("list")) {
                sessionTaskList.listTask();
            } else if (commandLine.equalsIgnoreCase("todo")) {
                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a todo cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------");
                    continue;
                }
                sessionTaskList.addToDo(lineArray);
            } else if (commandLine.equalsIgnoreCase("deadline")) {
                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a deadline cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------"); 
                    continue;
                }
                sessionTaskList.addDeadLine(lineArray);
            } else if (commandLine.equalsIgnoreCase("event")) {
                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a event cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------"); 
                    continue;
                }
                sessionTaskList.addEvent(lineArray);
            }
            else {
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t " + "Pleas enter a valid command");
                System.out.println("\t-----------------------------------------------------------------");
            }
        }
        input.close();
    }
}
