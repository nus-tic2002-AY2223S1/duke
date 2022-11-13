package common.exceptions;

import static common.constants.ErrorMessage.DUPLICATED_TASK_ERROR_MSG;

public class DuplicatedTaskException extends DukeException {
    public DuplicatedTaskException() {
        super(DUPLICATED_TASK_ERROR_MSG);
    }
}
