package exception;

public class LackDetailsException extends DukeException {
    public LackDetailsException(String wrongInput){
        super("☹ OOPS!!! The description of a "+wrongInput+ " cannot be empty.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}


