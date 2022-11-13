package tasklist;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // todo string to print
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toOutput() {
        return "T" + super.toOutput();
    }
}
