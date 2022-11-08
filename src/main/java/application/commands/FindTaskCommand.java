package application.commands;

import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeValidationException;

public class FindTaskCommand extends Command{
    private String keyword = null;

    /**
     * Filter Task By Dates command default constructor
     * Requires Tracker, Storage and date filter text
     */
    public FindTaskCommand(Tracker tracker, Storage storage, String keyword){
        super(tracker, storage);
        this.keyword = keyword;
    }

    /**
     * Abstract method that is overwritten
     * Handles filter by date range capability
     */
    @Override
    public void execute() throws DukeValidationException {
        this.tracker.find(keyword);
    }
}

