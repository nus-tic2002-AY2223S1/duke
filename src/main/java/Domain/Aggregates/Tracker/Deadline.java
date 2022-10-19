package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task{
    public LocalDateTime dueDateTime;
    protected String shortName = "D";


    public Deadline(String n) throws DukeValidationException {
        super(n);
        String[] f = CommonHelper.formatPassedName(n, "by");
        validate(f);
        this.name = f[0].trim();
        if(f[1].trim().split(" ").length > 1)
            this.dueDateTime = CommonHelper.convertStringToDateTime(f[1].trim());
        else
            this.dueDateTime = CommonHelper.convertStringToDate(f[1].trim());
    }

    public Deadline(int id, String name, String dueDateTime, boolean isDone) {
        super(id, name, isDone);
        this.dueDateTime = LocalDateTime.parse(dueDateTime);
    }

    @Override
    public void printItem(){
        String format = "[dd MMMM yyyy, hh:mm a]";
        if(this.dueDateTime.format(DateTimeFormatter.ofPattern("HH:mm")).equals("00:00"))
            format = "[dd MMMM yyyy]";
        String displayText = String.format("\t\t%d.[%s][%s] %s (by: %s)", this.id, this.shortName, this.isDone ? "X":" ", this.name, this.dueDateTime.format(DateTimeFormatter.ofPattern(format)));
        CommonHelper.printMessage(displayText);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass())
            return false;
        Deadline d = (Deadline) obj;
        return d.shortName.equals(this.shortName) && d.name.equals(this.name) && d.dueDateTime.equals(this.dueDateTime);
    }

    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.boolToInt(this.isDone), this.name, this.dueDateTime);
    }

    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        LocalDate _dueDateTime = this.dueDateTime.toLocalDate();
        if(end == null)
            return _dueDateTime.isEqual(start);
        return (_dueDateTime.isAfter(start) || _dueDateTime.isEqual(start)) && (_dueDateTime.isBefore(end) || _dueDateTime.isEqual(end));
    }

    private void validate(String[] f) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(f[0]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        else if(CommonHelper.isEmptyOrNull(f[1]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Due Date Time"));
    }

    public LocalDateTime getDueDateTime(){
        return this.dueDateTime;
    }
}
