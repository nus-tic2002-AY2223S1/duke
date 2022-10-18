package Application.Commands;

import Domain.Aggregates.Storage.Storage;
import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeFileException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

public abstract class Command{
    protected Tracker tracker;
    protected Storage storage;
    public Command(Tracker tracker, Storage storage){
        this.tracker = tracker;
        this.storage = storage;
    }

    public abstract void execute() throws DukeExistedException, DukeNotFoundException, DukeValidationException, DukeFileException;
}
