package duke.task;
import duke.exception.InvalidInputException;

public class ToDo extends Task {
    public static final long serialVersionUID = 01L;
    public ToDo(String description) throws InvalidInputException {
        super(description);
        typeIcon = "T";
    }
}