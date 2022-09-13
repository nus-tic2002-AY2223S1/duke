public class Todo extends Task{
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }
    public String toString() {
        return "[T]" + super.toString();
    }

}
