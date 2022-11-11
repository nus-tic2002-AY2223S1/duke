package Duke.Exceptions;

public class InvalidTaskCommand extends Exception{
    public InvalidTaskCommand(String errorMessage) {
        super(errorMessage);
    }
}
