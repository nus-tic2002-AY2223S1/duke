package exceptions;

public class FindTaskMissingDatetimeException extends DukeException {
    /**
     * FindTaskMissingDatetimeException constructor
     */
    public FindTaskMissingDatetimeException() {
        super(" ☹ OOPS!!! The datetime of a findtask command cannot be empty.");
    }
}