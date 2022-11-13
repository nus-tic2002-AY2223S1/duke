package Duke.Parser;

import Duke.Commands.Command;
import Duke.Commands.AddCommand;
import Duke.Commands.DeleteAllCommand;
import Duke.Commands.DeleteCommand;
import Duke.Commands.ExitCommand;
import Duke.Commands.FindCommand;
import Duke.Commands.ListCommand;
import Duke.Commands.MarkCommand;
import Duke.Commands.TagCommand;
import Duke.Commands.TimeCommand;
import Duke.Commands.UnmarkCommand;
import Duke.Exceptions.InvalidTaskCommand;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.Tasks.Events;
import Duke.Tasks.Deadlines;
import Duke.UI.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *  Parser class help to parse user's command
 */
public class Parser {

    /**
     *  Parses user input into command for execution.
     *
     * @param userInput is the full user's command to be parsed
     * @return a Command to be executed
     */
    public Command parseCommand(String userInput) throws InvalidTaskCommand, DateTimeException {

        String[] input = userInput.toLowerCase().split(" ", 2);

        final String command = input[0];
        String arguments = "";
        if (input.length>1){
            arguments = input[1];
        }

        try {
            switch (command) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand(arguments);
                case "mark":
                    if (arguments.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Mark, for example \"mark 2\"");
                    return new MarkCommand(Integer.parseInt(arguments));
                case "unmark":
                    if (arguments.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Unmark, for example \"unmark 2\"");
                    return new UnmarkCommand(Integer.parseInt(arguments));
                case "delete":
                    if (arguments.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Delete, for example \"delete 2\"");
                    Ui ui = new Ui();
                    if (arguments.equals("all")){
                        if (ui.getUserConfirmation("Are you sure you want to delete all task?")){
                            return new DeleteAllCommand();
                        } else {
                            return null;
                        }
                    }
                    if (ui.getUserConfirmation("Are you sure you want to delete task #"+arguments+"?")){
                        return new DeleteCommand(Integer.parseInt(arguments));
                    }
                    return null;
                case "tag":
                    String[] tagInput = arguments.split(" ", 2);
                    if (tagInput.length < 2)
                        throw new InvalidTaskCommand("Please enter a correct command for Tag, for example \"tag 2 #fun\"");
                    String tag = getTagging(tagInput[1]);
                    if (tag.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Tag, for example \"tag 2 #fun\"");
                    return new TagCommand(Integer.parseInt(tagInput[0]), tag);
                case "find":
                    if (arguments.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Find, for example \"find book\"");
                    return new FindCommand(arguments);
                case "time":
                    if (arguments.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Time, for example \"time 2\"");
                    return new TimeCommand(Integer.parseInt(arguments));
                case "todo":
                    if (arguments.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Todo, for example \"todo borrow book\"");
                    return new AddCommand(parseTask(command,arguments));
                case "deadline":
                    if (arguments.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Deadline, for example \"deadline return book /by 2/12/2019 1800\"");
                    return new AddCommand(parseTask(command,arguments));
                case "event":
                    if (arguments.isEmpty())
                        throw new InvalidTaskCommand("Please enter a correct command for Event, for example \"event project meeting /at 5/10/2019 1000\"");
                    return new AddCommand(parseTask(command,arguments));
                default:
                    throw new InvalidTaskCommand("Please enter a correct command");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskCommand("Please ensure parameter of command is valid");
        }
    }

    /**
     *  Parse command to add task command and identify if the task is todo, deadline or event
     *
     * @param command user's command like "todo, deadline, event"
     * @param argument user's parameter for the command like "return book /by 2/12/2019 1800, project meeting /at 5/10/2019 1000"
     * @return the task in their respective class
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
                return null;
        }
    }

    /**
     *  Parse due date and return String datetime in "d MMM yyyy HH:mm" format, example:
     *  2/12/2019 1800 -> 2 Dec 2019 18:00
     *  5/10/2019 1000 -> 5 Oct 2019 10:00
     *
     * @param argument user's parameter for the command like "return book /by 2/12/2019 1800, project meeting /at 5/10/2019 1000"
     * @return the parsed date time format
     */
    private String[] parseDueDate(String argument) throws DateTimeException {
        try {
            String[] argumentArr = {"", "no due date"}; //enum?
            if (argument.contains("/")){
                argumentArr = argument.trim().split("/by|/at", 2); //To get description and due date
                argumentArr[0] = argumentArr[0].trim();
                String dueDate = argumentArr[1].trim();
                LocalDateTime dtFormat = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm][d/MM/yyyy HHmm]"));
                argumentArr[1] = dtFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
            } else {
                argumentArr[0] = argument;
            }
            return argumentArr;
        } catch (Exception e) {
            throw new DateTimeException("Invalid datetime format, please follow [dd/MM/yyyy HHmm] or [d/MM/yyyy HHmm]");
        }
    }


    /**
     *  Retrieve tagging word with # from the command
     *
     * @param argument user's parameter for the command
     * @return the tagging word
     */
    private String getTagging(String argument){
        if (!argument.contains("#")){
            return "";
        }
        String[] argumentArr = argument.trim().split("#", 2);
        assert (argumentArr.length > 1);
        return "#" + argumentArr[1].trim();
    }
}
