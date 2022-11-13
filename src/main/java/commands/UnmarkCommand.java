package commands;

import entity.CommandParser;
import exceptions.DukeException;

public class UnmarkCommand extends Command {

    private final String inputs;

    public UnmarkCommand(String inputs) {
        this.inputs = inputs;
    }

    public void execute() {
        if (CommandParser.countCommandParts(inputs) < 2)
            throw new DukeException("☹ OOPS!!! Unmark is not specified");

        String inputBody = CommandParser.getCommandBody(inputs);
        int taskNo = Integer.parseInt(inputBody) - 1;

        if (taskNo >= instance.tasks.size())
            throw new DukeException("☹ OOPS!!! The task for unmarking is not in list.");
        instance.unmarkTask(taskNo);
    }
}
