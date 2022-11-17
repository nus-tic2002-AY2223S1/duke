package exceptions;

public class DukeException extends Exception{
        String error;
        public DukeException(String error){
            this.error=error;
        }

    }