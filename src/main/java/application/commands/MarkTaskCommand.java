package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class MarkTaskCommand extends Command{
    private final int id;

    /**
     * Mark Task command default constructor
     * Requires Tracker, Storage and ID
     */
    public MarkTaskCommand(Tracker tracker, Storage storage, int id) {
        super(tracker, storage);
        this.id = id;
    }

    /**
     * Abstract method that is overwritten
     * Handles mark capability - Marks task in Tracker as done
     */
    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        if(this.tracker.updateItem(id, true))
            this.storage.override(tracker.tasks);
    }
}
