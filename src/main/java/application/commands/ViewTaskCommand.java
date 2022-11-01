package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class ViewTaskCommand extends Command{

    /**
     * Delete Task command default constructor
     * Requires Tracker and Storage
     */
    public ViewTaskCommand(Tracker tracker, Storage storage) {
        super(tracker, storage);
    }

    /**
     * Abstract method that is overwritten
     * Handles view capability - Display list of tasks in Tracker
     */
    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        this.tracker.showList();
    }
}
