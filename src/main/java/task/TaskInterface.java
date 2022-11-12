package task;

/**
 * The interface to represent a task
 */
public interface TaskInterface {
    /**
     * to get the text for a task
     *
     * @return the text of a task
     */
    public String getString();
    /**
     * to check if the task is mark or unmark
     *
     * @return true for mark and false for unmark
     */
    public boolean isDone();
    /**
     * to mark a task as done or not done
     *
     * @param mark if true then done, false is not done
     */
    public void markTask(boolean mark);
    /**
     * to get the task string
     *
     * @return the string of a task
     */
    public String getWork();
    /**
     * to get the unique identifier of a task
     *
     * @return the long id of a task which is a timestamp
     */
    public long getID();
    /**
     * to get the endDate of a task
     *
     * @return the end date with specific format
     */
    public String getEndDate();
}
