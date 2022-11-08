package application.commands;

import application.helpers.CommonHelper;
import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeValidationException;

import java.time.LocalDate;

public class FilterTaskByDatesCommand extends Command{
    private final LocalDate start;
    private LocalDate end = null;

    /**
     * Filter Task By Dates command default constructor
     * Requires Tracker, Storage and date filter text
     */
    public FilterTaskByDatesCommand(Tracker tracker, Storage storage, String input) throws DukeValidationException {
        super(tracker, storage);
        String[] splitInput = input.split("to");
        if(splitInput.length > 1) {
            end = CommonHelper.convertStringToDate(splitInput[1].trim()).toLocalDate();
        }
        start = CommonHelper.convertStringToDate(splitInput[0].trim()).toLocalDate();
    }

    /**
     * Abstract method that is overwritten
     * Handles filter by date range capability
     */
    @Override
    public void execute() {
        this.tracker.filterByDates(start, end);
    }
}
