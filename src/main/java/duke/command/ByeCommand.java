package duke.command;

import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

/**
 * Print out a bye message and do necessary clean up
 */
public class ByeCommand extends Command {
    public ByeCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
        isExit = true;
    }

    @Override
    public void execute() {
        ui.printGoodbye();
    }

    @Override
    public String executeGui() throws DukeException, InvalidInputException {
        return "Bye. Hope to see you again soon!";
    }
}
