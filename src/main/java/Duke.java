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
        List<Task> tasks = new LinkedList<>();

        // greetings
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t-----------------------------------------------------------------");

       // declaration
        String inputLine;
        String commandLine;
        Scanner input = new Scanner(System.in);

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
            else if (commandLine.equalsIgnoreCase("mark")) {
                // get the index of the to do list to mark
                int inputIndex = Integer.parseInt(lineArray[1]);
                int arrayIndex = inputIndex - 1;
                tasks.get(arrayIndex).setDone(true);
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t\t " + tasks.get(arrayIndex));
                System.out.println("\t-----------------------------------------------------------------");
            }
            else if (commandLine.equalsIgnoreCase("unmark")) {
                // get the index of the to do list to unmark
                int inputIndex = Integer.parseInt(lineArray[1]);
                int arrayIndex = inputIndex - 1;
                tasks.get(arrayIndex).setDone(false);
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t\t " + tasks.get(arrayIndex));
                System.out.println("\t-----------------------------------------------------------------");
            }
            else if (commandLine.equalsIgnoreCase("list")) {
                System.out.println("\t-----------------------------------------------------------------");
                
                // if list is empty, prints empty list message
                if (tasks.size() == 0) {
                    System.out.println("\t The list is empty");
                }
                else 
                {
                    // prints out the entire list
                    for (int i = 0; i < tasks.size(); i++) {
                        int count = i + 1;
                        System.out.println("\t " + count + ". " + tasks.get(i));
                    }
                }
                
                System.out.println("\t-----------------------------------------------------------------");
            }
            else if (lineArray[0].equalsIgnoreCase("todo"))
            {
                
                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a todo cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------"); 
                    continue;
                }

                // piece the task description back together
                String descriptionLine = "";
                for (int i = 1; i < lineArray.length; i++) {
                    descriptionLine += " " + lineArray[i];
                }

                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t " + "Got it. I've added this task: ");
                tasks.add(new Todo(descriptionLine));
                System.out.println("\t\t " + tasks.get(tasks.size()-1));
                System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------------------------"); 
                
            } else if (lineArray[0].equalsIgnoreCase("deadline"))
            {

                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a deadline cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------"); 
                    continue;
                }
                
                // piece the task description back together
                String descriptionLine = "";
                int indexOfDelimiter = 0;
                for (int i = 1; i < lineArray.length; i++) {
                    if (lineArray[i].equalsIgnoreCase("/by")) {
                        indexOfDelimiter = i;
                        break;
                    }
                    descriptionLine += " " + lineArray[i];
                }

                String date = "";
                for (int i = indexOfDelimiter+1; i < lineArray.length; i++) {
                    date += " " + lineArray[i];
                }

                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t " + "Got it. I've added this task: ");
                tasks.add(new Deadline(descriptionLine, date));
                System.out.println("\t\t " + tasks.get(tasks.size()-1));
                System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------------------------"); 
                
            } else if (lineArray[0].equalsIgnoreCase("event"))
            {

                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a event cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------"); 
                    continue;
                }
                
                // piece the task description back together
                String descriptionLine = "";
                int indexOfDelimiter = 0;
                for (int i = 1; i < lineArray.length; i++) {
                    if (lineArray[i].equalsIgnoreCase("/at")) {
                        indexOfDelimiter = i;
                        break;
                    }
                    descriptionLine += " " + lineArray[i];
                }

                String date = "";
                for (int i = indexOfDelimiter+1; i < lineArray.length; i++) {
                    date += " " + lineArray[i];
                }
                

                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t " + "Got it. I've added this task: ");
                tasks.add(new Event(descriptionLine, date));
                System.out.println("\t\t " + tasks.get(tasks.size()-1));
                System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------------------------"); 
            }
            else 
            {
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t " + "Pleas enter a valid command");
                System.out.println("\t-----------------------------------------------------------------"); 
                continue;
                // add task to the list
                // Task t = new Task(line);
                // tasks.add(t);

                // // echo
                // System.out.println("\t-----------------------------------------------------------------");
                // System.out.println("\t " + "added: " + line);
                // System.out.println("\t-----------------------------------------------------------------");
            }

        }
        input.close();
    }
}
