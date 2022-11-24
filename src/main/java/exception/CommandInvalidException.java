package exception;

public class CommandInvalidException extends DukeException{
    public CommandInvalidException(String wrongInput) {
        super(wrongInput);
    }
}
