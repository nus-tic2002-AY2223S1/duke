package duke.exception;

import duke.constant.Constant;

/**
 * Custom exception class, used when perform deletion, reschedule and marking of empty task list.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class EmptyTaskListException extends DukeException {

    /**
     * Default constructors.
     */
    public EmptyTaskListException() {
        super("empty task list! please add in some tasks first");
        setCode(Constant.ResponseCode.ERROR_EMPTY_TASK_LIST);
    }
}
