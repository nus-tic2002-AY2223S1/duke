public class Deadline extends Task {
    protected String type = "D";

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return (this.due == null ? "\t[D]" + super.toString() : "\t[D]" + super.toString() + " (by: " + this.due + ")");
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