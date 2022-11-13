package domain.exceptions;

public class DukeException extends Exception {
    /**
     * Base Exception
     * Default constructor with string message
     * Prints exception in this format: {exceptionCode} - {errorMessage}
     */
    public DukeException(String message){
        System.out.println(String.format("\t%s - %s", this.getExceptionCode(this), message));
    }

    /**
     * Default constructor with exception
     * Prints exception in this format: {exceptionCode} - {errorMessage}
     */
    public DukeException(Exception ex) {
        ex.printStackTrace();
        System.out.println(String.format("\t%s - %s", this.getExceptionCode(ex), ex));
    }

    /**
     * Get exception code based on the type of exception that is thrown
     */
    private DukeExceptionCode getExceptionCode(Exception ex){
        String className = ex.getClass().getName().toLowerCase();
        if(className.contains("notfound")) {
            return DukeExceptionCode.NotFound;
        } else if(className.contains("existed")) {
            return DukeExceptionCode.Existed;
        } else if(className.contains("validation")) {
            return DukeExceptionCode.Validation;
        } else if(className.contains("argument")) {
            return DukeExceptionCode.Argument;
        } else {
            return DukeExceptionCode.Other;
        }
    }
}
