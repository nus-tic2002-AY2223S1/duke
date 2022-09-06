package task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.symbol = "D";
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.by);
    }
}