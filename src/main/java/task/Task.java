package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return status icon of either "X" or " " based on values of isDone variable.
     * If isDone = true, status icon will be "X" and " " vice versa.
     *
     * @return The status icon of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return description of task
     *
     * @return The description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark task as done by setting isDone variable to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as not done by setting isDone variable to false.
     */
    public void unMarkDone() {
        this.isDone = false;
    }

    /**
     * Return a string including both the status icon and description of task
     *
     * @return Both status icon and description in a string
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Return a string including both the status icon and description of task in format to be saved in task.txt
     *
     * @return Both status icon and description in a string
     */
    public String stringToOutput() {
        return " | " + (this.getStatusIcon() == "X" ? "1" : "0") + " | " + this.getDescription();
    }
}