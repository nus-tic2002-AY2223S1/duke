package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

public class ViewTaskCommand extends Command{

    public ViewTaskCommand(Tracker tracker, Storage storage) {
        super(tracker, storage);
    }

    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        this.tracker.showList();
    }
}
