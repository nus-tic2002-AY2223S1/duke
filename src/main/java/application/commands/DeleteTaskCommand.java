package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class DeleteTaskCommand extends Command{
    private final int id;

    public DeleteTaskCommand(Tracker tracker, Storage storage, int id) {
        super(tracker, storage);
        this.id = id;
    }

    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        if(this.tracker.deleteItem(id))
            this.storage.override(tracker.tasks);
    }
}
