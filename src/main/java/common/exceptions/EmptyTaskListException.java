package common.exceptions;

import static common.constants.ErrorMessage.EMPTY_TASK_LIST_ERROR_MSG;

public class EmptyTaskListException extends DukeException {
    public EmptyTaskListException() {
        super(EMPTY_TASK_LIST_ERROR_MSG);
    }
}
