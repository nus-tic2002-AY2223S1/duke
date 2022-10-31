package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.DukeValidationException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Event extends Task{
    protected LocalDateTime startDateTime;
    protected String shortName = "E";

    /**
     * Event is a Task
     * Event default constructor
     * Conversion from string to LocalDate and LocalDateTime done based on regex expression to find HH:mm
     */
    public Event(String n) throws DukeValidationException {
        super(n);
        String[] f = CommonHelper.formatPassedName(n, "at");
        validate(f);
        this.name = f[0].trim();
        Pattern p = Pattern.compile(".*([01]?[0-9]|2[0-3]):[0-5][0-9].*");
        if(p.matcher(f[1].trim()).matches())
            this.startDateTime = CommonHelper.convertStringToDateTime(f[1].trim());
        else
            this.startDateTime = CommonHelper.convertStringToDate(f[1].trim());
    }

    /**
     * Default constructor when converting values from .txt file to an Event object
     */
    public Event(int id, String name, String startDateTime, boolean isDone) {
        super(id, name, isDone);
        this.startDateTime = LocalDateTime.parse(startDateTime);
    }

    /**
     * Abstract method that is overwritten
     * To print out task in this format: {id}.[{shortName}] [{isDone}] {name} (by: {startDateTime})
     */
    @Override
    public void printItem(){
        String format = "[dd MMMM yyyy, hh:mm a]";
        if(this.startDateTime.format(DateTimeFormatter.ofPattern("HH:mm")).equals("00:00"))
            format = "[dd MMMM yyyy]";
        String displayText = String.format("\t\t%d.[%s][%s] %s (at: %s)", this.id, this.shortName, this.isDone ? "X":" ", this.name, this.startDateTime.format(DateTimeFormatter.ofPattern(format)));
        CommonHelper.printMessage(displayText);
    }

    /**
     * Abstract method that is overwritten
     * Compares 2 objects to see if they are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass())
            return false;
        Event e = (Event)obj;
        return e.shortName.equals(this.shortName) && e.name.equals(this.name) && e.startDateTime.equals(this.startDateTime);
    }

    /**
     * Abstract method that is overwritten
     * To convert object to string in this format: {id} | {shortName} | {isDone} | {name} | {startDateTime}
     */
    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.boolToInt(this.isDone), this.name, this.startDateTime.toString());
    }

    /**
     * Abstract method that is overwritten
     * Retrieves all Events within the start and end datetime range
     */
    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        LocalDate _startDateTime = this.startDateTime.toLocalDate();
        if(end == null)
            return _startDateTime.isEqual(start);
        return (_startDateTime.isAfter(start) || _startDateTime.isEqual(start)) && (_startDateTime.isBefore(end) || _startDateTime.isEqual(end));
    }

    /**
     * Event validation
     * Name and Start Date Time are mandatory
     */
    private void validate(String[] f) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(f[0]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        else if(CommonHelper.isEmptyOrNull(f[1]))
            throw new DukeValidationException(String.format(MessageConstants. TASK_VALIDATION_EMPTY_ERROR, "Start Date Time"));
    }

    /**
     * Getters
     */
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }
}
