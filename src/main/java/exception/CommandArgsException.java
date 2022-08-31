package exception;

import constant.CommonConstant;

public class IllegalCommandArgException extends DukeException {

    public IllegalCommandArgException(String message) {
        super(message);
        setCode(CommonConstant.ErrorCode.ILLEGAL_ARGUMENTS);
    }
}
