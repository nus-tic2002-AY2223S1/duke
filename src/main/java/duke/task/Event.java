package duke.task;
import duke.DateTime;
import duke.exception.InvalidInputException;

public class Event extends Task {
    public static final long serialVersionUID = 01L;
    protected DateTime at;

    public Event(String description, DateTime at) throws InvalidInputException {
        super(description);

        this.at = at;
        typeIcon = "E";
    }
    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return at.isSameDate(dateTime);
    };

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", at);
    }
}