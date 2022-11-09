package taskList;

public class Todo extends Task {
    public Todo(String description){
        super(description);
    }

    // todo string to print
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
