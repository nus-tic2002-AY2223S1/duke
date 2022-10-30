public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return (this.by == null ? "\t[D]" + super.toString() : "\t[D]" + super.toString() + " (by: " + by + ")");
    }
}