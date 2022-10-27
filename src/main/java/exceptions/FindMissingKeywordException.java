package exceptions;

public class FindMissingKeywordException extends DukeException {
    /**
     * FindMissingKeyword constructor
     */
    public FindMissingKeywordException() {
        super(" ☹ OOPS!!! Keyword is missing. The find command should be: find {keyword}");
    }
}
