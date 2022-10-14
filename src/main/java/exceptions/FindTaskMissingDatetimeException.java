package exceptions;

public class FindTaskMissingDatetimeException extends DukeException {
    public FindTaskMissingDatetimeException() {
        super(" ☹ OOPS!!! The datetime of a findtask command cannot be empty.");
    }
}