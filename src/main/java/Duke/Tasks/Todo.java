package Duke.Tasks;

/**
 * Represents a Todo in the task list.
 */
public class Todo extends Task {
    /**
     *  Constructor of Todo
     *
     * @param description to describe what needs to be done for the Todo
     */
    public Todo(String description) {
        super(description, "Todo");
    }

    /**
     * To retrieve the description of a Todo
     *
     * @return a String with the description of the Todo
     */
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
}