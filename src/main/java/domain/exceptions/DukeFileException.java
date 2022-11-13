package domain.exceptions;

public class DukeFileException extends DukeException{
    /**
     * DukeFileException is a DukeException
     * Thrown when error is reading or writing to a file
     */
    public DukeFileException(String message){
        super(message);
    }
}
