package duke;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import duke.command.Command;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;

public class Parser {
    // Delimiter for tokenization is a single whitespace
    public static final String DELIMITER = " ";

    protected Ui ui;
    protected TaskList tasks;

    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public Command parse(String fullCommand) throws InvalidInputException {
        HashMap<String, String> arguments = new HashMap<>();
        String[] tokens = fullCommand.split("\\s+");
        // If first token (command) is empty, there are empty spaces typed in at the front - so we remove it
        if (tokens[0].isEmpty()) {
            tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
        }
        if (tokens.length == 0) {
            throw new InvalidInputException(InputExceptionType.UNKNOWN_COMMAND);
        }
        arguments.put("command", tokens[0]);

        // Default key is "payload"
        String key = "payload";
        ArrayList<String> values = new ArrayList<>();
        for (int i = 1; i < tokens.length; ++i) {
            // Check whether this token is a new key
            if (tokens[i].charAt(0) == '/') {
                // If it is, save current value into the map and start a new k-v pair
                arguments.put(key, String.join(DELIMITER, values));
                key = tokens[i].substring(1);
                values.clear();
            }
            else {
                // If not, append this token to the end of the value
                values.add(tokens[i]);
            }
        }

        // Store the last k-v pair
        if (!values.isEmpty()) {
            arguments.put(key, String.join(DELIMITER, values));
        }

        // Initialize a respective class from the command (by capitalize first character)
        String className = tokens[0] + "Command";
        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        className = "duke.command." + className;
        try {
            Class<?> cls = Class.forName(className);
            Constructor<?> constructor = cls.getDeclaredConstructor(Ui.class, TaskList.class, HashMap.class);
            Object obj = constructor.newInstance(ui, tasks, arguments);
            return (Command) obj;
        } catch (Exception e) {
            // If any exception thrown above, it means the command is not formatted properly
            throw new InvalidInputException(InputExceptionType.UNKNOWN_COMMAND, e);
        }
    }
}