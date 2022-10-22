package exceptions;

public class DeadlineMissingKeywordException extends DukeException {
    /**
     * DeadlineMissingKeywordException constructor
     */
    public DeadlineMissingKeywordException() {
        super(" ☹ OOPS!!! The key word /by must exists in the deadline command.");
    }
}
