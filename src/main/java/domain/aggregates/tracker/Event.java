package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.DukeArgumentException;
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
     * Creates a new Event by formatting given input to Name and Due Date Time.
     *
     * @param input String.
     * @throws DukeArgumentException if invalid date string argument passed.
     * @throws DukeValidationException if name or start date time properties are empty.
     */
    public Event(String input) throws DukeValidationException, DukeArgumentException {
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
     * Creates a new Event with explicit values for Id, Name, Is Done and Start Date Time as it is converting values from .txt file.
     *
     * @param id Integer.
     * @param name String.
     * @param startDateTime String.
     * @param isDone boolean.
     */
    public Event(int id, String name, String startDateTime, boolean isDone) {
        super(id, name, isDone);
        this.startDateTime = LocalDateTime.parse(startDateTime);
    }

    /**
     * @inheritDoc
     * Prints out event in this format: {id}.[{shortName}] [{isDone}] {name} (by: {startDateTime}) .
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
     * @inheritDoc
     * If object of different type, it will be cast as Event and compare.
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
     * @inheritDoc
     * Convert event to string in this format: {id} | {shortName} | {isDone} | {name} | {startDateTime} .
     */
    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.booleanToInteger(this.isDone), this.name, this.startDateTime.toString());
    }

    /**
     * @inheritDoc
     * Retrieves all Events where start date time within the start and end datetime range.
     */
    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        LocalDate startDateTime = this.startDateTime.toLocalDate();
        if(end == null) {
            return startDateTime.isEqual(start);
        }
        return (startDateTime.isAfter(start) || startDateTime.isEqual(start)) && (startDateTime.isBefore(end) || startDateTime.isEqual(end));
    }

    /**
     * @inheritDoc
     * Postpones Event's start date time to either given or plus 1 by default.
     */
    @Override
    public void postpone(String remark, boolean isSpecified) throws DukeValidationException, DukeArgumentException {
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

    /**
     * @inheritDoc
     * Finds Events where either id or name or start date time matches.
     * If exception, returns false.
     */
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
     * Validates name and start date time passed from User Interface.
     *
     * @param formattedInputs String[].
     * @throws DukeValidationException if name or start date time are empty.
     */
    private void validate(String[] formattedInputs) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(formattedInputs[0])) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        } else if(CommonHelper.isEmptyOrNull(formattedInputs[1])) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Start Date Time"));
        }
    }

    /**
     * Getter of property.
     */
    public LocalDateTime getStartDateTime(){
        return this.startDateTime;
    }
}
