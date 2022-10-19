package Application.Commands;

import Application.Helpers.CommonHelper;
import Domain.Aggregates.Storage.Storage;
import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
