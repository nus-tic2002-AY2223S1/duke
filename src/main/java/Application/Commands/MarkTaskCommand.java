package Application.Commands;

import Domain.Aggregates.Storage.Storage;
import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeFileException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

public class MarkTaskCommand extends Command{
    private final int id;

    public MarkTaskCommand(Tracker tracker, Storage storage, int id) {
        super(tracker, storage);
        this.id = id;
    }

    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        if(this.tracker.updateItem(id, true))
            this.storage.override(tracker.tasks);
    }
}
