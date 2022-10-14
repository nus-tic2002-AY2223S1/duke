package exceptions;

public class EventMissingKeywordException extends DukeException {
    public EventMissingKeywordException() {
        super(" ☹ OOPS!!! The key word /at must exists in the event command.");
    }
}
