package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;

public class ViewTaskCommand extends Command{

    /**
     * Initialises ViewTaskCommand.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     */
    public ViewTaskCommand(Tracker tracker, Storage storage) {
        super(tracker, storage);
    }

    /**
     * @inheritDoc
     * Handles display of all tasks
     */
    @Override
    public void execute() {
        this.tracker.showList();
    }
}
