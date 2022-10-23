package exceptions;

public class InputWrongPriorityException extends DukeException {
    /**
     * InputWrongPriorityException constructor
     */
    public InputWrongPriorityException() {
        super(" ☹ OOPS!!! Priority can only be either low, medium, or high");
    }
}
