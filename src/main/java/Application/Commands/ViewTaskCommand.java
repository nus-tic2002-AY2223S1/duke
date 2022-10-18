package Application.Commands;

import Domain.Aggregates.Storage.Storage;
import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeFileException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

public class ViewTaskCommand extends Command{

    public ViewTaskCommand(Tracker tracker, Storage storage) {
        super(tracker, storage);
    }

    @Override
    public void execute() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        this.tracker.showList();
    }
}
