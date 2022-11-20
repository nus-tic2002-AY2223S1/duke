/**
 * The type Event.
 */
public class Event extends Task {

    private final String eventDate;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param eventDate   the event date
     */
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param isDone      the is done
     * @param eventDate   the event date
     */
    public Event(String description, boolean isDone, String eventDate) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    /**
     * Gets event date.
     *
     * @return the event date
     */
    public String getEventDate() {
        return eventDate;
    }

    @Override
    public String convertToString() {
        return "event " + this.description + " /at " + this.eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.eventDate + ")";
    }
}
