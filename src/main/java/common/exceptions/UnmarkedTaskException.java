package common.exceptions;

import static common.constants.ErrorMessage.UNMARKED_TASK_ERROR_MSG;

public class UnmarkedTaskException extends DukeException {
    public UnmarkedTaskException() {
        super(UNMARKED_TASK_ERROR_MSG);
    }
}
