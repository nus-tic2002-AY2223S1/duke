public class Todo extends Task{
    protected boolean isDone;

    public Todo(String description) {
        super(description);
    }
    public String toString() {
        return "[T]" + super.toString();
    }

}
