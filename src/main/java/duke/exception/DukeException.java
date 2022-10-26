package duke.exception;

import duke.constant.Constant;

/**
 * Custom exception class, used as base class for the exception class in the program.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class DukeException extends RuntimeException {

    /**
     * Error code.
     */
    protected int code;

    /**
     * Default constructors.
     *
     * @param message: Error message.
     */
    public DukeException(String message) {
        super(message);
        this.code = Constant.ResponseCode.ERROR_SERVICE_EXCEPTION;
    }

    /**
     * Set error code.
     *
     * @param code: Error code represent the type of exception.
     */
    protected void setCode(int code) {
        this.code = code;
    }

    /**
     * Get error message.
     *
     * @return Error message about the exception.
     */
    @Override
    public String getMessage() {
        return String.format("error code: %d, message: %s", code, super.getMessage());
    }
}
