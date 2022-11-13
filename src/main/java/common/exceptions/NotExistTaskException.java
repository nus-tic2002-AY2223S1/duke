package common.exceptions;

import static common.constants.ErrorMessage.NOT_EXIST_TASK_ERROR_MSG;

public class NotExistTaskException extends DukeException {
    public NotExistTaskException() {
        super(NOT_EXIST_TASK_ERROR_MSG);
    }
}
