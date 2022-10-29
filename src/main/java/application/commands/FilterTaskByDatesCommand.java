package application.commands;

import application.helpers.CommonHelper;
import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeValidationException;

import java.time.LocalDate;

public class FilterTaskByDatesCommand extends Command{
    private final LocalDate start;
    private LocalDate end = null;

    public FilterTaskByDatesCommand(Tracker tracker, Storage storage, String s) throws DukeValidationException {
        super(tracker, storage);
        String[] f = s.split("to");
        if(f.length > 1)
            end = CommonHelper.convertStringToDate(f[1].trim()).toLocalDate();
        start = CommonHelper.convertStringToDate(f[0].trim()).toLocalDate();
    }

    @Override
    public void execute() {
        this.tracker.filterByDates(start, end);
    }
}
