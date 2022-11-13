package common.exceptions;

import static common.constants.ErrorMessage.INVALID_TASK_DESCRIPTION_ERROR_MSG;

public class InvalidTaskDescriptionException extends DukeException {
    public InvalidTaskDescriptionException() {
        super(INVALID_TASK_DESCRIPTION_ERROR_MSG);
    }
}
