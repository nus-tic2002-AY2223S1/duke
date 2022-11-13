package exceptions;

public class DukeException extends RuntimeException {

    /**
     * duke exception constructor
     *
     * @param errorMessage get specific error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
