package Duke.Tasks;

/**
 * Represents a Todo in the task list.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public String getDescription() {
        //String mark = this.getStatus() ? "[X]" : "[ ]";
        return "[T]" + super.getDescription();
        //return description;
    }
}