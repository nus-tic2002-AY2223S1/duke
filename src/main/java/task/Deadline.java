package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Return a string including status icon, description and datetime of deadline
     *
     * @return Status icon, description and datetime of deadline in a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Return a string including status icon, description and datetime of deadline in format to be saved in task.txt
     *
     * @return Status icon, description and datetime of deadline in a string
     */
    @Override
    public String stringToOutput() {
        return "D" + super.stringToOutput() + " | " + by;
    }

    /**
     * Return the deadline of the task
     *
     * @return Datetime of deadline in string
     */
    public String getBy() {
        return by;
    }
}