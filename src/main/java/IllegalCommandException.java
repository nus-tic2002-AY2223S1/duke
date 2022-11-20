/**
 * The type Illegal command exception.
 */
public class IllegalCommandException extends Exception {

    /**
     * Instantiates a new Illegal command exception.
     *
     * @param message the message
     */
    public IllegalCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
