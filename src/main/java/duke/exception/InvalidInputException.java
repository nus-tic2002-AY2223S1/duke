package duke.exception;

/**
 * Exception to be thrown when there is a problem to parse the user-input command
 */
@SuppressWarnings("serial")
public class InvalidInputException extends Exception {
    public static enum InputExceptionType {
        UNKNOWN_COMMAND,
        EMPTY_DESCRIPTION,
        EMPTY_INDEX,
        NO_SEARCH_DATE,
        NO_BY_DATE,
        NO_AT_DATE,
        NOT_INTEGER,
        MALFORMED_DATE,
        INDEX_OUT_OF_BOUND
    }

    public InvalidInputException(InputExceptionType inputType) {
        this(inputType, null);
    }

    public InvalidInputException(InputExceptionType inputType, Throwable err) {
        super(getDescription(inputType), err);
    }

    public static String getDescription(InputExceptionType inputType) {
        switch (inputType) {
            case UNKNOWN_COMMAND:
                return "Command given is not recognized as a valid one.";
            case EMPTY_DESCRIPTION:
                return "You will need to provide a description.";
            case EMPTY_INDEX:
                return "An integer index must follow the command";
            case NO_SEARCH_DATE:
                return "You must specify a date after searchdate command.";
            case NO_BY_DATE:
                return "You must specify a deadline date following /by keyword.";
            case NO_AT_DATE:
                return "You must specify an event date following /at keyword.";
            case MALFORMED_DATE:
                return "Date (and time) specified cannot be parsed.";
            case NOT_INTEGER:
                return "Only integer is accepted as the argument.";
            case INDEX_OUT_OF_BOUND:
                return "We don't have an existing entry with this index.";

            default:
                return "This type of invalid input is not recognized.";
        }
    }
}