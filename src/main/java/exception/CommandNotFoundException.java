package exception;

import constant.CommonConstant;

public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException(String message) {
        super(message);
        setCode(CommonConstant.ErrorCode.COMMAND_NOT_FOUND);
    }
}
