package nus.duke.tasklist;
/**
 * Represents a Todo task type extends from Task.
 */
public class Todo extends Task{
    /**
     * Constructor for Todo task type.
     */
    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        return super.getDescription();
    }
    public String getDateAndTime(int mode) {
        return null;
    }

    @Override
    public String toString(int mode) {
        return "[T]" + super.toString(1);
    }

}