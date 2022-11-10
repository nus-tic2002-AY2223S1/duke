package exception;

public class DukeException extends RuntimeException {
    
    /**
     * DukeException constructor
     */
    public DukeException() {
    }
    
    /**
     * DukeException constructor with error message
     *
     * @param message error message
     */
    public DukeException(String message) {
        super(message);
    }
}
