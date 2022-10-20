
package duke.task;

import java.io.Serializable;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
@SuppressWarnings("serial")
public abstract class Task implements Serializable {
    protected String typeIcon = "*";
    protected String description;
    protected boolean isDone;

    public Task(String description) throws InvalidInputException {
        if (description.isEmpty()) {
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
        this.isDone = false;
    }
    public String toString() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
}
