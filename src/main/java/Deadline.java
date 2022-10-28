public class Deadline extends Task {
    
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (this.by == null) {
            return "[D]" + super.toString();
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
    
}
