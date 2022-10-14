package exceptions;

public class EventWrongFormatException extends DukeException {
    public EventWrongFormatException() {
        super(" ☹ OOPS!!! Input has wrong format. Event command should be: event {description} /at {date}");
    }
}
