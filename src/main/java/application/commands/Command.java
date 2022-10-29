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
    public Command(Tracker tracker, Storage storage){
        this.tracker = tracker;
        this.storage = storage;
    }

    public abstract void execute() throws DukeExistedException, DukeNotFoundException, DukeValidationException, DukeFileException;
}
