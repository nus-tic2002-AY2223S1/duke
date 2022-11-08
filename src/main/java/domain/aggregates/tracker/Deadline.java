package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.DukeValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Deadline extends Task{
    /**
     * Properties
     */
    protected LocalDateTime dueDateTime;
    protected String shortName = "D";
    private Pattern p = Pattern.compile(".*([01]?[0-9]|2[0-3]):[0-5][0-9].*");

    /**
     * Deadline is a Task
     * Deadline default constructor
     * Conversion from string to LocalDate and LocalDateTime done based on regex expression to find HH:mm
     */
    public Deadline(String n) throws DukeValidationException {
        super(n);
        String[] f = CommonHelper.formatPassedName(n, "by");
        validate(f);
        this.name = f[0].trim();
        if(p.matcher(f[1].trim()).matches())
            this.dueDateTime = CommonHelper.convertStringToDateTime(f[1].trim());
        else
            this.dueDateTime = CommonHelper.convertStringToDate(f[1].trim());
    }

    /**
     * Default constructor when converting values from .txt file to a Deadline object
     */
    public Deadline(int id, String name, String dueDateTime, boolean isDone) {
        super(id, name, isDone);
        this.dueDateTime = LocalDateTime.parse(dueDateTime);
    }

    /**
     * Abstract method that is overwritten
     * To print out task in this format: {id}.[{shortName}] [{isDone}] {name} (by: {dueDateTime})
     */
    @Override
    public void printItem(){
        String format = "[dd MMMM yyyy, hh:mm a]";
        if(this.dueDateTime.format(DateTimeFormatter.ofPattern("HH:mm")).equals("00:00"))
            format = "[dd MMMM yyyy]";
        String displayText = String.format("\t\t%d.[%s][%s] %s (by: %s)", this.id, this.shortName, this.isDone ? "X":" ", this.name, this.dueDateTime.format(DateTimeFormatter.ofPattern(format)));
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
        Deadline d = (Deadline) obj;
        return d.shortName.equals(this.shortName) && d.name.equals(this.name) && d.dueDateTime.equals(this.dueDateTime);
    }

    /**
     * Abstract method that is overwritten
     * To convert object to string in this format: {id} | {shortName} | {isDone} | {name} | {dueDateTime}
     */
    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.boolToInt(this.isDone), this.name, this.dueDateTime);
    }

    /**
     * Abstract method that is overwritten
     * Retrieves all Deadlines within the start and end datetime range
     */
    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        LocalDate _dueDateTime = this.dueDateTime.toLocalDate();
        if(end == null)
            return _dueDateTime.isEqual(start);
        return (_dueDateTime.isAfter(start) || _dueDateTime.isEqual(start)) && (_dueDateTime.isBefore(end) || _dueDateTime.isEqual(end));
    }

    @Override
    public void update(String remarks, boolean isSpecified) throws DukeValidationException {
        if(!CommonHelper.isEmptyOrNull(remarks) && isSpecified) {
            if (p.matcher(remarks.trim()).matches())
                this.dueDateTime = CommonHelper.convertStringToDateTime(remarks.trim());
            else
                this.dueDateTime = CommonHelper.convertStringToDate(remarks.trim());
        } else if(!isSpecified)
            this.dueDateTime = this.dueDateTime.plusDays(1);
        else
            throw new DukeValidationException(MessageConstants.TASK_SNOOZE_DATETIME_NOT_PASSED);
    }

    /**
     * Deadline validation
     * Name and Due Date Time are mandatory
     */
    private void validate(String[] f) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(f[0]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        else if(CommonHelper.isEmptyOrNull(f[1]))
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Due Date Time"));
    }

    /**
     * Getters
     */
    public LocalDateTime getDueDateTime(){
        return this.dueDateTime;
    }
}
