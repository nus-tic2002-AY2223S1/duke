package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class UnmarkTaskCommand extends Command{
    private final int id;

    /**
     * Unmark Task command default constructor
     * Requires Tracker, Storage and ID
     */
    public UnmarkTaskCommand(Tracker tracker, Storage storage, int id) {
        super(tracker, storage);
        this.id = id;
    }

    /**
     * Abstract method that is overwritten
     * Handles unmark capability - Mark task in Tracker as not done
     */
    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        if(this.tracker.hasItemUpdated(id, false)) {
            this.storage.override(tracker.tasks);
        }
    }
}
