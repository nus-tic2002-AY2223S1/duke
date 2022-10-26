package exceptions;

public class BasicInputException extends DukeException {
    /**
     * BasicInputException constructor
     */
    public BasicInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + "I only accept these commands {\"bye\", \"mark\", \"unmark\", \"deadline\", \"todo\", \"list\", \"delete\", \"event\", \"findtask\", \"priority\", \"find\", \"dowithinperiod\"}");
    }
}
