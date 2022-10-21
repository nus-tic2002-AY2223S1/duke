package duke.exception;

import duke.constant.Constant;

public class EmptyTaskListException extends DukeException {

    public EmptyTaskListException() {
        super("empty task list! please add in some tasks first");
        setCode(Constant.ResponseCode.ERROR_EMPTY_TASK_LIST);
    }
}
