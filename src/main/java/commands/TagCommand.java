package commands;

import entity.CommandParser;
import exceptions.DukeException;
import java.util.Arrays;

public class TagCommand extends Command{

    private final String inputs;
    public TagCommand(String inputs) {
        this.inputs = inputs;
    }

    public void execute() {
        if (CommandParser.countCommandParts(inputs) < 2)
            throw new DukeException("☹ OOPS!!! The description of a tag cannot be empty.");

        String inputBody = CommandParser.getCommandBody(inputs);
        String[] tagDesc = CommandParser.getTags(inputBody);
        int taskNo = Integer.parseInt(tagDesc[0]) - 1;

        if (taskNo >= instance.tasks.size())
            throw new DukeException("☹ OOPS!!! The task for tagging is not in list.");

        instance.tagTask(taskNo, Arrays.copyOfRange(tagDesc, 1, tagDesc.length));
    }
}
