package exceptions;

public class DukeException extends Exception{

    public String errorMsg;
    public DukeException(String errorMsg){
        this.errorMsg = errorMsg;
    }
    public String getMessage() {
        return this.errorMsg;
    }
}
