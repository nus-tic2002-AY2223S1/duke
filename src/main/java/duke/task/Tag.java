package duke.task;
import duke.DateTime;
import duke.exception.InvalidInputException;


public class Tag extends Task {
    public static final long serialVersionUID = 01L;
    protected static String tag;

    public Tag(String tag) throws InvalidInputException {
        super(tag);
        this.tagIcon = tag;
        this.tag = tag;
    }

    @Override
    public String toString() {
        super.toString();
        return super.toString();
    }

    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return false;
    };
}