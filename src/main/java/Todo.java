/**
 * The type Todo.
 */
public class Todo extends Task {
    /**
     * Instantiates a new Todo.
     *
     * @param description the description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates a new Todo.
     *
     * @param description the description
     * @param isDone      the is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String convertToString() {
        return "todo " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
