package Duke;
public class Event extends Task {

    protected String type = "E";


    public Event(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return (this.due == null ? "\t[E]" + super.toString() : "\t[E]" + super.toString() + " (at: " + this.due + ")");
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