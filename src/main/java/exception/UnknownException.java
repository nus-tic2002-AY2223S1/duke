package exception;

public class UnknownException extends RuntimeException {

    public UnknownException(String message) {
        super(message);
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
