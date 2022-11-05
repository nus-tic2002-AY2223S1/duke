package exceptions;

public class DeadlineWrongFormatException extends DukeException {
    /**
     * DeadlineWrongFormatException constructor
     */
    public DeadlineWrongFormatException() {
        super("OOPS!!! Input has wrong format. Deadline command should be: deadline {description} /by {deadline}");
    }
}
