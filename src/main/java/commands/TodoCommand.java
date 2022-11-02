package commands;

import entity.CommandParser;
import entity.Todo;
import exceptions.DukeException;

public class TodoCommand extends Command{

    private final String inputs;

    public TodoCommand(String inputs) {
        this.inputs = inputs;
    }

    public void execute() {
        if (CommandParser.countCommandParts(inputs) < 2)
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");

        String inputBody = CommandParser.getCommandBody(inputs);
        Todo todo = new Todo(inputBody);
        instance.addTask(todo);
    }
}
