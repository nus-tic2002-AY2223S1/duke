package application.commands;

import application.helpers.CommonHelper;
import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeArgumentException;

import java.time.LocalDate;

public class FilterTaskByDatesCommand extends Command{
    private final LocalDate start;
    private LocalDate end = null;

    /**
     * Initialises FilterTaskByDatesCommand.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     * @param input String.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public FilterTaskByDatesCommand(Tracker tracker, Storage storage, String input) throws DukeArgumentException {
        super(tracker, storage);
        String[] splitInput = input.split("to");
        if(splitInput.length > 1) {
            end = CommonHelper.convertStringToDate(splitInput[1].trim()).toLocalDate();
        }
        start = CommonHelper.convertStringToDate(splitInput[0].trim()).toLocalDate();
    }

    /**
     * @inheritDoc
     * Handles filter by date range capability
     */
    @Override
    public void execute() {
        this.tracker.filterByDates(start, end);
    }
}
