/**
 * The type Task.
 */
public abstract class Task {
    /**
     * The Description.
     */
    protected final String description;
    /**
     * The Is done.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     * @param isDone      the is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description.trim();
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark as done.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark as not done.
     */
    protected void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Is done boolean.
     *
     * @return the boolean
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Convert to string string.
     *
     * @return the string
     */
    public abstract String convertToString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}