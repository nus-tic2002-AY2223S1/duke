public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String type) {
        super("☹ OOPS!!! The description of a "+type+" cannot be empty.");
    }
}
