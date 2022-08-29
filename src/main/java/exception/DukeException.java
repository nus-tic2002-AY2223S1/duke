package exception;

import constant.CommonConstant;

public class DukeException extends RuntimeException {

    protected int code;

    public DukeException(String message) {
        super(message);
        this.code = CommonConstant.ErrorCode.SERVICE_EXCEPTION;
    }

    protected void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return String.format("error code: %d, message: %s", code, super.getMessage());
    }
}
