package Domain.Exceptions;

public class DukeException extends Exception {
    private String code;
    public DukeException(String message){
        System.out.println(String.format("\t%s - %s", this.getExceptionCode(this), message));
    }

    public DukeException(Exception ex) {
        ex.printStackTrace();
        System.out.println(String.format("\t%s - %s", this.getExceptionCode(ex), ex));
    }

    private DukeExceptionCode getExceptionCode(Exception ex){
        String className = ex.getClass().getName().toLowerCase();
        if(className.contains("notfound"))
            return DukeExceptionCode.NotFound;
        else if(className.contains("existed"))
            return DukeExceptionCode.Existed;
        else if(className.contains("validation"))
            return DukeExceptionCode.Validation;
        else
            return DukeExceptionCode.Other;

    }
}
