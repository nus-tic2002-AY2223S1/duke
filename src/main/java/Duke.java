import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
                
        // System.out.println("Hello from\n" + logo);

        // instantiate a list to store strings
        List<Task> toDoList = new LinkedList<>();

        // greetings
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t-----------------------------------------------------------------");

        // user input and echo
        while (true) {
            // user input from console
            String line;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            // instantiate an array to tokenize the input
            String[] lineArray = line.split(" ");

            // if input is "bye", prints goodbye message and terminate
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------------------------");
                break;
            } 
            else if (lineArray[0].equalsIgnoreCase("mark")) {
                // get the index of the to do list to mark
                int inputIndex = Integer.parseInt(lineArray[1]);
                int arrayIndex = inputIndex - 1;
                toDoList.get(arrayIndex).setDone(true);
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t\t " + "[X] " + toDoList.get(arrayIndex).getDescription());
                System.out.println("\t-----------------------------------------------------------------");
            }
            else if (lineArray[0].equalsIgnoreCase("unmark")) {
                // get the index of the to do list to unmark
                int inputIndex = Integer.parseInt(lineArray[1]);
                int arrayIndex = inputIndex - 1;
                toDoList.get(arrayIndex).setDone(false);
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t\t " + "[ ] " + toDoList.get(arrayIndex).getDescription());
                System.out.println("\t-----------------------------------------------------------------");
            }
            else if (lineArray[0].equalsIgnoreCase("list")) {
                System.out.println("\t-----------------------------------------------------------------");
                
                // if list is empty, prints empty list message
                if (toDoList.size() == 0) {
                    System.out.println("\t The list is empty");
                }
                else 
                {
                    // prints out the entire list
                    for (int i = 0; i < toDoList.size(); i++) {
                        int count = i + 1;

                        if (toDoList.get(i).isDone()) {
                            System.out.println("\t " + count + ". " + "[X] " + toDoList.get(i).getDescription());
                        }
                        else
                        {
                            System.out.println("\t " + count + ". " + "[ ] " + toDoList.get(i).getDescription());
                        }
                        
                    }
                }
                
                System.out.println("\t-----------------------------------------------------------------");
            }
            else 
            {
                // add task to the list
                Task t = new Task(line);
                toDoList.add(t);

                // echo
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t " + "added: " + line);
                System.out.println("\t-----------------------------------------------------------------");
                
            }
        }
    }
}
