package Application.Commands;

import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

public abstract class Command {
    protected Tracker tracker;
    public Command(Tracker tracker){
        this.tracker = tracker;
    }

    public abstract void execute();

    public abstract void validate() throws DukeExistedException, DukeNotFoundException, DukeValidationException;
}
