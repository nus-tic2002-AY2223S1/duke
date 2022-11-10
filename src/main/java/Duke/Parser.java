package Duke;

import Duke.Commands.Command;
import Duke.Commands.AddCommand;
import Duke.Commands.ExitCommand;
import Duke.Commands.MarkCommand;
import Duke.Commands.ListCommand;
import Duke.Commands.UnmarkCommand;
import Duke.Commands.DeleteCommand;
import Duke.Tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            System.out.println("Parse does not match");
        }

        final String command = matcher.group("command").toLowerCase().trim();
        final String arguments = matcher.group("arguments").toLowerCase().trim();

        int target = -1;

        if (!arguments.isEmpty()){
            target = Character.getNumericValue(arguments.charAt(0));
        }

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(target);
            case "unmark":
                return new UnmarkCommand(target);
            case "delete":
                return new DeleteCommand(target);
            case "todo":
                return new AddCommand(parseTask(command,arguments));
            case "deadline":
                return new AddCommand(parseTask(command,arguments));
            case "event":
                return new AddCommand(parseTask(command,arguments));
            default:
                System.out.println("Wrong input");
                return null;
        }
    }

    /**
     *  Parse command to add task to task list like:
     *  todo borrow book
     *  deadline return book /by 2/12/2019 1800
     *  event project meeting /at 5/10/2019 1000
     */
    private Task parseTask(String command, String argument){
        String[] argumentArr = parseDueDate(argument);
        switch (command){
            case "todo":
                return new Todo(argumentArr[0]);
            case "deadline":
                return new Deadlines(argumentArr[0],argumentArr[1]);
            case "event":
                return new Events(argumentArr[0],argumentArr[1]);
            default:
                System.out.println("Invalid command");
                //throw new Duke.InvalidTaskCreation();
                return null;
        }
    }

    /**
     *  Parse due date and return String datetime in "d MMM yyyy HH:mm" format, example:
     *  2/12/2019 1800 -> 2 Dec 2019 18:00
     *  5/10/2019 1000 -> 5 Oct 2019 10:00
     */
    private String[] parseDueDate(String argument){
        String[] argumentArr = {"", "no due date"}; //enum?
        if (argument.contains("/")){
            argumentArr = argument.trim().split("/by", 2); //To get description and due date

            argumentArr[0] = argumentArr[0].trim();
            String dueDate = argumentArr[1].trim();
            LocalDateTime dtFormat = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm][d/MM/yyyy HHmm]"));
            argumentArr[1] = dtFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
        } else {
            argumentArr[0] = argument;
        }
        return argumentArr;
    }

}
