package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeValidationException;

public class FindTaskCommand extends Command{
    private String keyword = null;

    /**
     * Initialises FindTaskCommand.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     * @param keyword String.
     */
    public FindTaskCommand(Tracker tracker, Storage storage, String keyword){
        super(tracker, storage);
        this.keyword = keyword;
    }

    /**
     * @inheritDoc
     * Handles filter by date range capability.
     */
    @Override
    public void execute() throws DukeValidationException {
        this.tracker.find(keyword);
    }
}

