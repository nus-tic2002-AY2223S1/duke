package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime startDateTime;
    protected String shortName = "E";

    public Event(String n) throws DukeValidationException {
        super(n);
        String[] f = CommonHelper.formatPassedName(n, "at");
        validate(f);
        this.name = f[0].trim();
        if(f[1].trim().split(" ").length > 1)
            this.startDateTime = CommonHelper.convertStringToDateTime(f[1].trim());
        else
            this.startDateTime = CommonHelper.convertStringToDate(f[1].trim());
    }

    public Event(int id, String name, String startDateTime, boolean isDone) {
        super(id, name, isDone);
        this.startDateTime = LocalDateTime.parse(startDateTime);
    }

    @Override
    public void printItem(){
        String format = "[dd MMMM yyyy, hh:mm a]";
        if(this.startDateTime.format(DateTimeFormatter.ofPattern("HH:mm")).equals("00:00"))
            format = "[dd MMMM yyyy]";
        String displayText = String.format("\t\t%d.[%s][%s] %s (by: %s)", this.id, this.shortName, this.isDone ? "X":" ", this.name, this.startDateTime.format(DateTimeFormatter.ofPattern(format)));
        CommonHelper.printMessage(displayText);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass())
            return false;
        Event e = (Event)obj;
        return e.shortName.equals(this.shortName) && e.name.equals(this.name) && e.startDateTime.equals(this.startDateTime);
    }

    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.boolToInt(this.isDone), this.name, this.startDateTime.toString());
    }

    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        LocalDate _startDateTime = this.startDateTime.toLocalDate();
        if(end == null)
            return _startDateTime.isEqual(start);
        return (_startDateTime.isAfter(start) || _startDateTime.isEqual(start)) && (_startDateTime.isBefore(end) || _startDateTime.isEqual(end));
    }

    private void validate(String[] f) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(f[0]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        else if(CommonHelper.isEmptyOrNull(f[1]))
            throw new DukeValidationException(String.format(MessageConstants. TASK_VALIDATION_EMPTY_ERROR, "Start Date Time"));
    }

    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }
}
