package exceptions;

public class TodoMissingDescriptionException extends DukeException {
    /**
     * TodoMissingDescriptionException constructor
     */
    public TodoMissingDescriptionException() {
        super(" ☹ OOPS!!! The description of a todo cannot be empty.");
    }
}