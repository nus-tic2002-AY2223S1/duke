package duke.task;
import duke.DateTime;
import duke.exception.InvalidInputException;


/**
 * An event class, which has a dateTime instance marking its time range
 */
public class Event extends Task {
    public static final long serialVersionUID = 01L;
    protected DateTime at;
    protected DateTime to;

    /**
     * Initialize the event task and set the typeIcon to 'E'
     * @param description Description of the task, cannot be empty
     * @param at "datetime" The date and time this event falls at
     * @throws InvalidInputException This is thrown if the task description is null or an empty string
     */

    public Event(String description, DateTime at, DateTime to) throws InvalidInputException {
        super(description);

        this.at = at;
        this.to = to;
        typeIcon = "E";
    }

    /**
     * Check whether this event occurring date ('at' datetime) falls on the given dateTime
     * @param dateTime The date for comparison
     * @return True when this task falls on the given `dateTime`
     */
    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return at.isSameDate(dateTime);
    };

    /**
     * Converts this task to a descriptive string
     */
    @Override
    public String toString() {
        return super.toString() + String.format("(at: %s - %s)", at,to);
    }
}