package exception;

import constant.Constant;

public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException(String message) {
        super(message);
        setCode(Constant.ErrorCode.COMMAND_NOT_FOUND);
    }
}
