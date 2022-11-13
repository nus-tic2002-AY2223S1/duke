package duke.task;
import duke.DateTime;
import duke.exception.InvalidInputException;

/**
 * A deadline task, with a 'by' dateTime indicating when it has to be done by
 */
public class Deadline extends Task{
    public static final long serialVersionUID = 01L;
    protected DateTime by;
    public Deadline(String description, DateTime by) throws InvalidInputException {

        /**
         * Initialize the deadline task and set the typeIcon to 'D'
         * @param description Description of the task, cannot be empty
         * @param by "datetime" The date and time this task has to be done before
         * @throws InvalidInputException This is thrown if the task description is null or an empty string
         */
        super(description);
        typeIcon = "D";
        this.by = by;
    }

    /**
     * Check whether this deadline ('by' datetime) falls on the given dateTime
     * @param dateTime The date for comparison
     * @return True when this task falls on the given `dateTime`
     */
    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return by.isSameDate(dateTime);
    };

    /**
     * Converts this task to a descriptive string
     */
    @Override
    public String toString(){
        return super.toString() + String.format("(by: %s)",by);
    }
}