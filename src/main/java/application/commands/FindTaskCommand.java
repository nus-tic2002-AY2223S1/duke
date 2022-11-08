package application.commands;

import application.helpers.CommonHelper;
import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeValidationException;

import java.time.LocalDate;

public class FindTaskCommand extends Command{
    private String keyword = null;

    /**
     * Filter Task By Dates command default constructor
     * Requires Tracker, Storage and date filter text
     */
    public FindTaskCommand(Tracker tracker, Storage storage, String s){
        super(tracker, storage);
        keyword = s;
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

