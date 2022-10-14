package exceptions;

public class DeadlineMissingKeywordException extends DukeException {
    public DeadlineMissingKeywordException() {
        super(" ☹ OOPS!!! The key word /by must exists in the deadline command.");
    }
}
