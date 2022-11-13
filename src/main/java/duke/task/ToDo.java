package duke.task;
import duke.DateTime;
import duke.exception.InvalidInputException;

/**
 *  todo task, with no special fields and attributes
 */
public class ToDo extends Task {
    public static final long serialVersionUID = 1L;

    /**
     * Initialize the todo task and set the typeIcon to 'T'
     * @param description Description of the task, cannot be empty
     * @throws InvalidInputException This is thrown if the task description is null or an empty string
     */
    public ToDo(String description) throws InvalidInputException {
        super(description);
        typeIcon = "T";
    }

    /**
     * Since there is no specific date and time related to this task, so return false
     */
    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return false;
    };
}