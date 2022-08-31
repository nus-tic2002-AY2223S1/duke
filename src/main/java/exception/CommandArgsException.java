package exception;

import constant.CommonConstant;

public class CommandArgsException extends DukeException {

    public CommandArgsException(String message) {
        super(message);
        setCode(CommonConstant.ErrorCode.ILLEGAL_ARGUMENTS);
    }
}
