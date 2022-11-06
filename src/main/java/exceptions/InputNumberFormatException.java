package exceptions;

public class InputNumberFormatException extends DukeException {
    /**
     * InputNumberFormatException constructor
     */
    public InputNumberFormatException(String inputCommand) {
        super("OOPS!!! The index after " + inputCommand.split(" ")[0] + " is not numeric, please enter an numeric number.");
    }
}
