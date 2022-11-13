package duke.task;
import duke.DateTime;
import duke.exception.InvalidInputException;

/**
 * A tag class, which can give a tag for the task
 */
public class Tag extends Task {
    public static final long serialVersionUID = 01L;
    protected static String tag;

    public Tag(String tag) throws InvalidInputException {
        super(tag);
        this.tagIcon = tag;
        this.tag = tag;
    }

    /**
     * Converts this task to a descriptive string
     */
    @Override
    public String toString() {
        super.toString();
        return super.toString();
    }

    /**
     * Since there is no specific date and time related to this task, so return false
     */
    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return false;
    };
}