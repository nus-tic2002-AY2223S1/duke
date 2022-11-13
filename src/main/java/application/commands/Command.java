package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;
import domain.exceptions.DukeArgumentException;


public abstract class Command{
    protected Tracker tracker;
    protected Storage storage;

    /**
     * Initialises Command.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     */
    public Command(Tracker tracker, Storage storage){
        this.tracker = tracker;
        this.storage = storage;
    }

    /**
     * Executes command.
     *
     * @throws DukeExistedException if adds task that already exists.
     * @throws DukeNotFoundException if modifies a task that does not exist.
     * @throws DukeValidationException if required task properties are empty.
     * @throws DukeFileException if unable to save changes to local file.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public abstract void execute() throws DukeExistedException, DukeNotFoundException, DukeValidationException, DukeFileException, DukeArgumentException;
}
