package exceptions;

public class DukeException extends Exception {

    public String errorMsg;

    public DukeException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Return an error message that was passed in
     *
     * @return Error message
     */
    public String getMessage() {
        return this.errorMsg;
    }
}
