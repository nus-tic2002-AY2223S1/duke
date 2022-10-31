package Duke;
public class Todo extends Task {

    protected String type = "T";

    public Todo(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return (this.due == null ? "\t[T]" + super.toString() : "\t[T]" + super.toString() + " (by: " + this.due + ")");
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getIsDone() {
        return this.isDone ? "1" : "0";
    }
}