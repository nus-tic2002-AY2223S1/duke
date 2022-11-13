package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.InvalidInputException;
import duke.exception.SaveException;
import duke.exception.DukeException;

/**
 * class of all the commands, providing necessary interfaces and methods for implementation
 */
public abstract class Command {
    protected Ui ui;
    protected TaskList tasks;
    protected HashMap<String, String> arguments;
    protected Boolean isExit = false;

    public Command(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        this.ui = ui;
        this.tasks = tasks;
        this.arguments = arguments;
    }

    public Boolean isExit() {
        return this.isExit;
    }

    public abstract void execute() throws InvalidInputException, IOException, SaveException;

    public abstract String executeGui() throws DukeException, InvalidInputException, SaveException, IOException;
}