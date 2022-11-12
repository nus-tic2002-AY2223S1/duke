package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description.toUpperCase());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
}
