package duke.exception;

import duke.constant.Constant;

public class InvalidRescheduleTaskException extends DukeException {

    public InvalidRescheduleTaskException() {
        super("selected task is not `event` or `deadline` task, cannot be rescheduled");
        setCode(Constant.ResponseCode.ERROR_INVALID_RESCHEDULE_TASK);
    }
}
