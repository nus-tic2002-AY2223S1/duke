package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeArgumentException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class MarkTaskCommand extends Command{
    private final int id;

    /**
     * Initialises MarkTaskCommand.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     * @param id Integer.
     */
    public MarkTaskCommand(Tracker tracker, Storage storage, int id) {
        super(tracker, storage);
        this.id = id;
    }

    /**
     * @inheritDoc
     * Handles mark task as done and Updates to local data file.
     */
    @Override
    public void execute() throws DukeFileException, DukeArgumentException, DukeNotFoundException {
        if(this.tracker.hasItemStateUpdated(id, true)) {
            this.storage.override(tracker.tasks);
        }
    }
}
