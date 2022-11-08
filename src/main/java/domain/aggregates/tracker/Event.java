package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.DukeValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Event extends Task{
    /**
     * Properties
     */
    protected LocalDateTime startDateTime;
    protected String shortName = "E";
    private Pattern dateTimePattern = Pattern.compile(".*([01]?[0-9]|2[0-3]):[0-5][0-9].*");
    private Pattern datePattern = Pattern.compile(".*([01]?[0-9]|2[0-3])");

    /**
     * Event is a Task
     * Event default constructor
     * Conversion from string to LocalDate and LocalDateTime done based on regex expression to find HH:mm
     */
    public Event(String input) throws DukeValidationException {
        super(input);
        String[] formattedInputs = CommonHelper.formatPassedName(input, "at");
        validate(formattedInputs);
        this.name = formattedInputs[0].trim();
        if(dateTimePattern.matcher(formattedInputs[1].trim()).matches()) {
            this.startDateTime = CommonHelper.convertStringToDateTime(formattedInputs[1].trim());
        } else if(datePattern.matcher(formattedInputs[1].trim()).matches()) {
            this.startDateTime = CommonHelper.convertStringToDate(formattedInputs[1].trim());
        }
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
        if(this.startDateTime.format(DateTimeFormatter.ofPattern("HH:mm")).equals("00:00")) {
            format = "[dd MMMM yyyy]";
        }
        String displayText = String.format("\t\t%d.[%s][%s] %s (at: %s)", this.id, this.shortName, this.isDone ? "X":" ", this.name, this.startDateTime.format(DateTimeFormatter.ofPattern(format)));
        CommonHelper.printMessage(displayText);
    }

    /**
     * Abstract method that is overwritten
     * Compares 2 objects to see if they are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass()) {
            return false;
        }
        Event event = (Event)obj;
        return event.shortName.equals(this.shortName) && event.name.equals(this.name) && event.startDateTime.equals(this.startDateTime);
    }

    /**
     * Abstract method that is overwritten
     * To convert object to string in this format: {id} | {shortName} | {isDone} | {name} | {startDateTime}
     */
    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.booleanToInteger(this.isDone), this.name, this.startDateTime.toString());
    }

    /**
     * Abstract method that is overwritten
     * Retrieves all Events within the start and end datetime range
     */
    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        LocalDate startDateTime = this.startDateTime.toLocalDate();
        if(end == null) {
            return startDateTime.isEqual(start);
        }
        return (startDateTime.isAfter(start) || startDateTime.isEqual(start)) && (startDateTime.isBefore(end) || startDateTime.isEqual(end));
    }

    @Override
    public void update(String remark, boolean isSpecified) throws DukeValidationException {
        if(!CommonHelper.isEmptyOrNull(remark) && isSpecified) {
            if(dateTimePattern.matcher(remark.trim()).matches()) {
                this.startDateTime = CommonHelper.convertStringToDateTime(remark.trim());
            } else if(datePattern.matcher(remark.trim()).matches()) {
                this.startDateTime = CommonHelper.convertStringToDate(remark.trim());
            }
        } else if(!isSpecified) {
            this.startDateTime = this.startDateTime.plusDays(1);
        } else {
            throw new DukeValidationException(MessageConstants.TASK_SNOOZE_DATETIME_NOT_PASSED);
        }
    }

    @Override
    public boolean find(String keyword) {
        boolean result;
        try {
            result = this.id == CommonHelper.getNumber(keyword);
        } catch (Exception ex) {
            result = this.name.toLowerCase().contains(keyword.toLowerCase());
            try {
                if (!result){
                    if(dateTimePattern.matcher(keyword.trim()).matches()) {
                        result = this.startDateTime.equals(CommonHelper.convertStringToDateTime(keyword.trim()));
                    } else if(datePattern.matcher(keyword.trim()).matches()) {
                        result = this.startDateTime.equals(CommonHelper.convertStringToDate(keyword.trim()));
                    }
                }
            } catch (Exception e) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Event validation
     * Name and Start Date Time are mandatory
     */
    private void validate(String[] formattedInputs) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(formattedInputs[0])) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        } else if(CommonHelper.isEmptyOrNull(formattedInputs[1])) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Start Date Time"));
        }
    }

    /**
     * Getters
     */
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }
}
