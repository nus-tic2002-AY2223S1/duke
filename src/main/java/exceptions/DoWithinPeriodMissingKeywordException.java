package exceptions;

public class DoWithinPeriodMissingKeywordException extends DukeException {
    /**
     * DoWithinPeriodMissingKeywordException constructor
     */
    public DoWithinPeriodMissingKeywordException(String keyword) {
        super(" ☹ OOPS!!! The key word " + keyword + " must exists in the dowithinperiod command.");
    }
}
