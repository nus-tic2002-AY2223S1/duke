package Duke.Commands;

import Duke.Messages;
import Duke.TaskList;
import Duke.Storage;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command{
    public String execute(Storage storage, TaskList taskList) {
        return Messages.MESSAGE_GOODBYE;
    }
}
