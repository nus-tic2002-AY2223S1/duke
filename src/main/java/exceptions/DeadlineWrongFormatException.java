package exceptions;

public class DeadlineWrongFormatException extends DukeException {
    public DeadlineWrongFormatException(){
        super(" ☹ OOPS!!! Input has wrong format. Task.Deadline command should be: deadline {description} /by {deadline}");
    }
}
