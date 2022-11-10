package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public abstract String execute(Storage storage, TaskList task);
}
