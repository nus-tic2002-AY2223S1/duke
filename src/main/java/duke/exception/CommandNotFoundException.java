package duke.exception;

import duke.constant.Constant;

/**
 * Custom exception class, used when command is not found invalid when parse command.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class CommandNotFoundException extends DukeException {

    /**
     * Default constructors.
     *
     * @param message: Error message.
     */
    public CommandNotFoundException(String message) {
        super(message);
        setCode(Constant.ResponseCode.ERROR_COMMAND_NOT_FOUND);
    }
}
