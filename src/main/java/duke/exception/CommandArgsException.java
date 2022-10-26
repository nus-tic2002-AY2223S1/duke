package duke.exception;

import duke.constant.Constant;

/**
 * Custom exception class, used when arguments are invalid when parse command.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class CommandArgsException extends DukeException {

    /**
     * Default constructors.
     *
     * @param message: Error message.
     */
    public CommandArgsException(String message) {
        super(message);
        setCode(Constant.ResponseCode.ERROR_ILLEGAL_ARGUMENTS);
    }
}
