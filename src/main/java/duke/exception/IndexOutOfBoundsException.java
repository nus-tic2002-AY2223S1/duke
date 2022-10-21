package duke.exception;

import duke.constant.Constant;

public class IndexOutOfBoundsException extends DukeException {

    public IndexOutOfBoundsException() {
        super("given index is invalid, it should be less than current task size");
        setCode(Constant.ResponseCode.ERROR_INDEX_EXCEED_MAX_TASK_SIZE);
    }
}
