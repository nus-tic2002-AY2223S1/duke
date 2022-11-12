package exception;

public class LackDetailsException extends DukeException {
    public LackDetailsException(String wrongInput){
        super("☹ OOPS!!! The description of a "+wrongInput+ " cannot be empty. \n " +
                "you may key 'help' to see some command example");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}


