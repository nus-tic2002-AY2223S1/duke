package duke;

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.task.Task;
public class Duke {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        //data add
        Vector<Task> tasks = new Vector<>();
        ActHandler.greetingHandler();
        HelperFunction.printlnWithIndent(LONG_LINE);

        Scanner in = new Scanner(System.in);
        Boolean isExit = false;

        while(!isExit){
        String line = in.nextLine();
            // Split the line by any whitespaces characters (including spaces, tabs etc.)
        String[] arguments = line.split("\\s+");
            // If first argument (command) is empty, there are empty spaces typed in at the front - so we remove it
            if (arguments[0].isEmpty()) {
                arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            }

            HelperFunction.printlnWithIndent(LONG_LINE);

            try {
                switch(arguments[0]) {
                    case "bye":
                        ActHandler.byeHandler();
                        isExit = true;
                        break;
                    case "list":
                        ActHandler.listHandler(tasks);
                        break;
                    case "done":
                        ActHandler.doneHandler(tasks, arguments);
                        break;
                    case "notdone":
                        ActHandler.notdone(tasks, arguments);
                        break;
                    case "delete":
                        ActHandler.deleteHandler(tasks, arguments);
                        break;
                    case "deadline":
                        ActHandler.deadlineHandler(tasks, arguments);
                        break;
                    case "event":
                        ActHandler.eventHandler(tasks, arguments);
                        break;
                    case "todo":
                        ActHandler.todoHandler(tasks, arguments);
                        break;
                    default:
                        throw new InvalidInputException(InputExceptionType.UNKNOWN_COMMAND);
                }
            } catch (Exception e) {
                HelperFunction.printlnWithIndent ("Oops! " + e.getMessage());

        }
            HelperFunction.printlnWithIndent(LONG_LINE);
        }
        in.close();
    }


}
