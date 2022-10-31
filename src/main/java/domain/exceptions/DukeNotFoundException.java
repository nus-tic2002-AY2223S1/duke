package domain.exceptions;

public class DukeNotFoundException extends DukeException{
    /**
     * DukeNotFoundException is a DukeException
     * Thrown when requested item cannot be found
     */
    public DukeNotFoundException(String message) {
        super(message);
    }
}
