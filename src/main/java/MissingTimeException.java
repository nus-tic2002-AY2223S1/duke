public class MissingTimeException extends Exception {
    public MissingTimeException() {
        super("Event or Deadline Require time as a second argument.\n"+
                "\t\tExample:\n"+
                "\t\t\tdeadline return book /by Sunday\n"+
                "\t\t\tevent project meeting /at Mon 2-4pm");
    }
}
