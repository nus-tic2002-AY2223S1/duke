package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public abstract class Command{
    protected Tracker tracker;
    protected Storage storage;

    /**
     * Command default constructor
     * Requires Tracker and Storage to be used when executing
     */
    public Command(Tracker tracker, Storage storage){
        this.tracker = tracker;
        this.storage = storage;
    }

    /**
     * Abstract method to be overwritten for handling command execution
     */
    public abstract void execute() throws DukeExistedException, DukeNotFoundException, DukeValidationException, DukeFileException;
}
