package Duke.Tasks;

/**
 * Represents a Deadline in the task list.
 */
public class Deadlines extends Task {
    protected String due;

    /**
     *  Constructor of Deadlines
     *
     * @param description to describe what needs to be done for the Deadline
     * @param due is the due date of the Deadline
     */
    public Deadlines(String description, String due) {
        super(description,"Deadlines");
        this.due = due;
    }

    /**
     * To retrieve the description of a Deadline
     *
     * @return a String with the description of the Deadline
     */
    public String getDescription() {
        return "[D]" + super.getDescription() + " (" + due +")";
    }

    /**
     * To retrieve the due date of a Deadline
     *
     * @return a String with the due date of the Deadline
     */
    public String getDueDate(){
        return due;
    }
}