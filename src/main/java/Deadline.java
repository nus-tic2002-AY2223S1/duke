public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String[] parts = this.by.split(" ", 2);
        return "[D]" +  super.toString() + " (" + parts[0] + ": " + parts[1] + ")";
    }
}
