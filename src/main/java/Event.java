public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return (this.at == null ? "\t[E]" + super.toString() : "\t[E]" + super.toString() + " (at: " + at + ")");
    }
}