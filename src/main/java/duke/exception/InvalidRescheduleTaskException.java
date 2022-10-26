package duke.exception;

import duke.constant.Constant;

/**
 * Custom exception class, used when user tries to reschedule a task which is not event or deadline type.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class InvalidRescheduleTaskException extends DukeException {

    /**
     * Default constructors.
     */
    public InvalidRescheduleTaskException() {
        super("selected task is not `event` or `deadline` task, cannot be rescheduled");
        setCode(Constant.ResponseCode.ERROR_INVALID_RESCHEDULE_TASK);
    }
}
