package task;

public class Event extends Task {

    protected String at;

    /**
     * Event task constructor
     *
     * @param description task descriptions
     * @param at          event datetime
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Return a string including status icon, description, datetime and priority of event
     *
     * @return Status icon, description, datetime and priority of event in a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")" + " [" + this.getPriority() + "]";
    }

    /**
     * Return a string including status icon, description, datetime and priority of event in format to be saved in task.txt
     *
     * @return Status icon, description, datetime and priority of event in a string
     */
    @Override
    public String stringToOutput() {
        return "E" + super.stringToOutput() + " | " + at + " | " + super.getPriority();
    }

    /**
     * Return the event datetime of the task
     *
     * @return Datetime of event in string
     */
    public String getAt() {
        return at;
    }
}