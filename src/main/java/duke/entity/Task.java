package duke.entity;

import java.util.Objects;

/**
 * Entity class to represent a task without type.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class Task {

    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Flag to mark if the task is done.
     */
    protected boolean isDone;

    /**
     * Type of task.
     */
    protected String type;

    /**
     * Multi args constructor.
     *
     * @param description: Command description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get readable string representation for labeling if task is done.
     *
     * @return String instance which represents done or undone status of task.
     */
    private String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    /**
     * Get description.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description.
     *
     * @param description: Description of task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Check if the task is done.
     *
     * @return True is the task is done, otherwise value is false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Set done flag.
     *
     * @param done: Flag which label if the task is completed.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Get type of the task.
     *
     * @return Type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Set type of the task.
     *
     * @param type: Type of the task.
     */
    protected void setType(String type) {
        this.type = type;
    }

    /**
     * Get string representation of task type.
     *
     * @return Type of the task.
     */
    private String getTypeIcon() {
        return Objects.nonNull(type) ? getType() : " ";
    }

    /**
     * String representation of class.
     *
     * @return Readable string representation for instance description.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
}
