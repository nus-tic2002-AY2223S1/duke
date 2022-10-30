public class Todo extends Task {

    protected String by;

    public Todo(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return (this.by == null ? "\t[T]" + super.toString() : "\t[T]" + super.toString() + " (by: " + by + ")");
    }
}