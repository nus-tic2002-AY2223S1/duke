public class InvalidDeleteOptionException extends Throwable {
    public InvalidDeleteOptionException(int index, int size) {
        super("\tTried to Delete :"+ index + 1 + " List has only "+size+"item(s).");
    }
}
