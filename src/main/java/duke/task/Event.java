package duke.task;
import duke.exception.InvalidInputException;

public class Event extends Task {
    public static final long serialVersionUID = 01L;
    protected String at;

    public Event(String description, String at) throws InvalidInputException {
        super(description);

        this.at = at;
        typeIcon = "E";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", at);
    }
}