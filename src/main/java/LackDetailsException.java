public class LackDetailsException extends Exception {
    public LackDetailsException(){
        super();
    }

    public LackDetailsException(String errorMessage){
        super(errorMessage);
    }
}
