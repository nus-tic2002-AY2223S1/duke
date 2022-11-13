package domain.exceptions;

public class DukeValidationException extends DukeException{
    /**
     * DukeValidationException is a DukeException
     * Thrown when properties are null/empty when it is required
     */
    public DukeValidationException(String message) {
        super(message);
    }
}
