package common.exceptions;

import static common.constants.ErrorMessage.MARKED_TASK_ERROR_MSG;

public class MarkedTaskException extends DukeException {
    public MarkedTaskException() {
        super(MARKED_TASK_ERROR_MSG);
    }
}
