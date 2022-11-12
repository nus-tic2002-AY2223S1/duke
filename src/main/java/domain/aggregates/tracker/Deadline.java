package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.DukeArgumentException;
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
    private Pattern dateTimePattern = Pattern.compile(".*([01]?[0-9]|2[0-3]):[0-5][0-9].*");
    private Pattern datePattern = Pattern.compile(".*([01]?[0-9]|2[0-3])");

    /**
     * Creates a new Deadline by formatting given input to Name and Due Date Time.
     *
     * @param input String.
     * @throws DukeArgumentException if invalid date string argument passed.
     * @throws DukeValidationException if name or due date time properties are empty.
     */
    public Deadline(String input) throws DukeArgumentException, DukeValidationException {
        super(input);
        String[] formattedInputs = CommonHelper.formatPassedName(input, "by");
        validate(formattedInputs);
        this.name = formattedInputs[0].trim();
        if(dateTimePattern.matcher(formattedInputs[1].trim()).matches()) {
            this.dueDateTime = CommonHelper.convertStringToDateTime(formattedInputs[1].trim());
        } else if(datePattern.matcher(formattedInputs[1].trim()).matches()) {
            this.dueDateTime = CommonHelper.convertStringToDate(formattedInputs[1].trim());
        }
    }

    /**
     * Creates a new Deadline with explicit values for Id, Name, Is Done and Due Date Time as it is converting values from .txt file.
     *
     * @param id Integer.
     * @param name String.
     * @param dueDateTime String.
     * @param isDone boolean.
     */
    public Deadline(int id, String name, String dueDateTime, boolean isDone) {
        super(id, name, isDone);
        this.dueDateTime = LocalDateTime.parse(dueDateTime);
    }

    /**
     * @inheritDoc
     * Prints out deadline in this format: {id}.[{shortName}] [{isDone}] {name} (by: {dueDateTime}).
     */
    @Override
    public void printItem(){
        String format = "[dd MMMM yyyy, hh:mm a]";
        if(this.dueDateTime.format(DateTimeFormatter.ofPattern("HH:mm")).equals("00:00")) {
            format = "[dd MMMM yyyy]";
        }
        String displayText = String.format("\t\t%d.[%s][%s] %s (by: %s)", this.id, this.shortName, this.isDone ? "X":" ", this.name, this.dueDateTime.format(DateTimeFormatter.ofPattern(format)));
        CommonHelper.printMessage(displayText);
    }

    /**
     * @inheritDoc
     * If object of different type, it will cast as Deadline and compare.
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        return deadline.shortName.equals(this.shortName) && (deadline.name.toLowerCase()).equals(this.name.toLowerCase()) && deadline.dueDateTime.equals(this.dueDateTime);
    }

    /**
     * @inheritDoc
     * Convert deadline to string in this format: {id} | {shortName} | {isDone} | {name} | {dueDateTime} .
     */
    @Override
    public String toString(){
        return String.format("%d | %s | %d | %s | %s", this.id, this.shortName, CommonHelper.booleanToInteger(this.isDone), this.name, this.dueDateTime);
    }

    /**
     * @inheritDoc
     * Retrieves all Deadlines where due date time within the start and end datetime range.
     */
    @Override
    public boolean compare(LocalDate start, LocalDate end) {
        LocalDate dueDateTime = this.dueDateTime.toLocalDate();
        if(end == null) {
            return dueDateTime.isEqual(start);
        }
        return (dueDateTime.isAfter(start) || dueDateTime.isEqual(start)) && (dueDateTime.isBefore(end) || dueDateTime.isEqual(end));
    }

    /**
     * @inheritDoc
     * Postpones Deadline's due date time to either given or plus 1 by default.
     */
    @Override
    public void postpone(String remark, boolean isSpecified) throws DukeValidationException, DukeArgumentException {
        if(!CommonHelper.isEmptyOrNull(remark) && isSpecified) {
            if(dateTimePattern.matcher(remark.trim()).matches()) {
                this.dueDateTime = CommonHelper.convertStringToDateTime(remark.trim());
            } else if(datePattern.matcher(remark.trim()).matches()) {
                this.dueDateTime = CommonHelper.convertStringToDate(remark.trim());
            }
        } else if(!isSpecified) {
            this.dueDateTime = this.dueDateTime.plusDays(1);
        } else {
            throw new DukeValidationException(MessageConstants.TASK_SNOOZE_DATETIME_NOT_PASSED);
        }
    }

    /**
     * @inheritDoc
     * Find Deadlines where either id or name or due date time matches.
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
                    if (dateTimePattern.matcher(keyword.trim()).matches()) {
                        result = this.dueDateTime.equals(CommonHelper.convertStringToDateTime(keyword.trim()));
                    } else if (datePattern.matcher(keyword.trim()).matches()) {
                        result = this.dueDateTime.toLocalDate().equals(CommonHelper.convertStringToDate(keyword.trim()).toLocalDate());
                    }
                }
            } catch (Exception e) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Validates name and due date time passed from User Interface.
     *
     * @param formattedInputs String[].
     * @throws DukeValidationException if name or due date time are empty.
     */
    private void validate(String[] formattedInputs) throws DukeValidationException{
        if(CommonHelper.isEmptyOrNull(formattedInputs[0])) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Description"));
        } else if(CommonHelper.isEmptyOrNull(formattedInputs[1])) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Due Date Time"));
        }
    }

    /**
     * Getter of property
     */
    public LocalDateTime getDueDateTime(){
        return this.dueDateTime;
    }
}
