package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class SnoozeTaskCommand extends Command{

    private int id;
    private String newDateTime;

    public SnoozeTaskCommand(Tracker tracker, Storage storage, int id, String newDateTime){
        super(tracker, storage);
        this.id = id;
        this.newDateTime = newDateTime;
    }

    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        if(this.tracker.snooze(id, newDateTime))
            this.storage.override(tracker.tasks);
    }
}
