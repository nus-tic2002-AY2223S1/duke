public class Events extends Task {
    protected String by;

    public Events(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getData() { return "E" + super.getData() + "|" + by;}
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }

}
