package commands;

import entity.CommandParser;
import exceptions.DukeException;

public class FindCommand extends Command{
    private final String inputs;

    public FindCommand(String inputs) {
        this.inputs = inputs;
    }

    public void execute() {
        if (CommandParser.countCommandParts(inputs) < 2)
            throw new DukeException("☹ OOPS!!! Mark is not specified.");
        String inputBody = CommandParser.getCommandBody(inputs);
        instance.findTasks(inputBody);
    }


}
