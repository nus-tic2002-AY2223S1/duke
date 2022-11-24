package exception;

public class DukeException extends RuntimeException{
    public DukeException(String wrongInput){
        super(wrongInput);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}


//    public static void printException(String input){
//        try{
//            throw new LackDetailsException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
//        } catch (LackDetailsException e){
//            System.out.println("\t"+e.getMessage());
//        }
//    }
