package domain.exceptions;

public class DukeExistedException extends DukeException{
    /**
     * DukeExistedException is a DukeException
     * Thrown when duplicates are being created
     */
    public DukeExistedException(String message) {
        super(message);
    }
}
