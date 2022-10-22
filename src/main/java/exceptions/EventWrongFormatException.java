package exceptions;

public class EventWrongFormatException extends DukeException {
    /**
     * EventWrongFormatException constructor
     */
    public EventWrongFormatException() {
        super(" ☹ OOPS!!! Input has wrong format. Event command should be: event {description} /at {date}");
    }
}
