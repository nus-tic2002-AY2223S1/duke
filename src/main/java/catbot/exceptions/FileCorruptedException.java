package catbot.exceptions;

public class FileCorruptedException extends Exception{
    public FileCorruptedException(){}
    public FileCorruptedException(String message){
        super(message);
    }
}

