package duke.exception;

import duke.constant.Constant;

public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException(String message) {
        super(message);
        setCode(Constant.ResponseCode.ERROR_COMMAND_NOT_FOUND);
    }
}
