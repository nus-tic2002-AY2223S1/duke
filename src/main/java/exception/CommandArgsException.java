package exception;

import constant.Constant;

public class CommandArgsException extends DukeException {

    public CommandArgsException(String message) {
        super(message);
        setCode(Constant.ErrorCode.ILLEGAL_ARGUMENTS);
    }
}
