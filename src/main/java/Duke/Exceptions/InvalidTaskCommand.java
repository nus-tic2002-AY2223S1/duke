package Duke.Exceptions;

/**
 * Exception to handle invalid user command
 */
public class InvalidTaskCommand extends Exception{
    public InvalidTaskCommand(String errorMessage) {
        super(errorMessage);
    }
}
