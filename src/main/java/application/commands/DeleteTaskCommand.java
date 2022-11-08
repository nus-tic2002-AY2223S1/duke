package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeArgumentException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class DeleteTaskCommand extends Command{
    private final int id;

    /**
     * Initialises DeleteTaskCommand.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     * @param id Integer.
     */
    public DeleteTaskCommand(Tracker tracker, Storage storage, int id) {
        super(tracker, storage);
        this.id = id;
    }

    /**
     * @inheritDoc
     * Handles delete task and Updates to local data file.
     */
    @Override
    public void execute() throws DukeFileException, DukeArgumentException, DukeNotFoundException {
        if(this.tracker.hasItemDeleted(id)) {
            this.storage.override(tracker.tasks);
        }
    }
}
