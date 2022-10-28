public class Event extends Task {
    
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (this.at == null) {
            return "[E]" + super.toString();
        } else {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

}
