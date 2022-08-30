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
        List<String> toDoList = new LinkedList<>();

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
                        System.out.println("\t " + count + ". " + toDoList.get(i));
                        
                    }
                }
                
                System.out.println("\t-----------------------------------------------------------------");
            }
            else 
            {
                // add string to the list
                toDoList.add(line);

                // echo
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t " + "added: " + line);
                System.out.println("\t-----------------------------------------------------------------");
                
            }
        }
    }
}
