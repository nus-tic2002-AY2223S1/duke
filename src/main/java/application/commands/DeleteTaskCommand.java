package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class DeleteTaskCommand extends Command{
    private final int id;

    /**
     * Delete Task command default constructor
     * Requires Tracker, Storage and ID
     */
    public DeleteTaskCommand(Tracker tracker, Storage storage, int id) {
        super(tracker, storage);
        this.id = id;
    }

    /**
     * Abstract method that is overwritten
     * Handles delete capability - Deleting from Tracker and Updating to local data file
     */
    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        if(this.tracker.deleteItem(id))
            this.storage.override(tracker.tasks);
    }
}
