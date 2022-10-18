package taskpackage;

public class Todo extends Task{
    protected boolean isDone;

    public Todo(String description) {
        super(description);
    }

    public String getData() { return "T" + super.getData();}
    public String toString() {
        return "[T]" + super.toString();
    }

}
