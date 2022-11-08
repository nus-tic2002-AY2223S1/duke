package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeArgumentException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class SnoozeTaskCommand extends Command{

    private int id;
    private String newDateTime;
    private boolean isNewDateTimeSpecified;

    /**
     * Initialises SnoozeTaskCommand.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     * @param id Integer.
     * @param newDateTime String.
     * @param isNewDateTimeSpecified boolean.
     */
    public SnoozeTaskCommand(Tracker tracker, Storage storage, int id, String newDateTime, boolean isNewDateTimeSpecified){
        super(tracker, storage);
        this.id = id;
        this.newDateTime = newDateTime;
        this.isNewDateTimeSpecified = isNewDateTimeSpecified;
    }

    /**
     * @inheritDoc
     * Handles snooze task to given date else plus 1 day to task's current date time.
     * Applicable for Event and Deadline task type only.
     */
    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException, DukeArgumentException {
        if(this.tracker.hasItemSnoozed(id, newDateTime, isNewDateTimeSpecified)) {
            this.storage.override(tracker.tasks);
        }
    }
}
