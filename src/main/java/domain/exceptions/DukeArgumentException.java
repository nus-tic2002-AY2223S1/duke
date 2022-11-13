package domain.exceptions;

public class DukeArgumentException extends DukeException{
    /**
     * DukeArgumentException is a DukeException
     * Thrown when arguments passed are null/empty
     */
    public DukeArgumentException(String message) {
        super(message);
    }
}
