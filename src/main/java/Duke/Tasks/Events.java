package Duke.Tasks;

/**
 * Represents an Event in the task list.
 */
public class Events extends Task{
    protected String due;

    /**
     *  Constructor of Events
     *
     * @param description to describe what needs to be done for the Events
     * @param due is the due date of the Events
     */
    public Events(String description, String due) {
        super(description,"Events");
        this.due = due;
    }

    /**
     * To retrieve the description of a Events
     *
     * @return a String with the description of the Events
     */
    public String getDescription() {
        return "[E]" + super.getDescription() + " (" + due +")";
    }

    /**
     * To retrieve the due date of a Events
     *
     * @return a String with the due date of the Events
     */
    public String getDueDate(){
        return due;
    }
}
