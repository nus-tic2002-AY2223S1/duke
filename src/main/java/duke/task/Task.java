
package duke.task;

import java.io.Serializable;
import duke.DateTime;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;

public abstract class Task implements Serializable {

    public static final long serialVersionUID = 1L;
    protected String typeIcon = "*";
    protected String description;
    protected boolean isDone;

    public Task(String description) throws InvalidInputException {
        if (description == null || description.isEmpty()) {
            throw new InvalidInputException(InputExceptionType.EMPTY_DESCRIPTION);
        }
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        // Return tick when done, or 'X' symbol when it is not done
        return (isDone ? "\u2713" : "\u2718");
    }
    public String getTypeIcon() {
        return typeIcon;
    }

    public String getDescription() {
        return description;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsnotDone() {
        isDone = false;
    }
    public abstract Boolean isSameDate(DateTime dateTime);
    public String toString() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
}
