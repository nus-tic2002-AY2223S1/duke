package duke.exception;

import duke.constant.Constant;

/**
 * Custom exception class, used when user tries to access the index of task beyond maximum index.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class IndexOutOfBoundsException extends DukeException {

    /**
     * Default constructors.
     */
    public IndexOutOfBoundsException() {
        super("given index is invalid, it should be less than current task size");
        setCode(Constant.ResponseCode.ERROR_INDEX_EXCEED_MAX_TASK_SIZE);
    }
}
