package task;


public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Convert task done (true/false) to (X/ )
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Read task description
     */
    public String getTask() {
        return description;
    }
    /**
     * Indicate task done
     */
    public void mark() {
        isDone = true;
        //System.out.println("Nice! I've marked this task as done:\n [" + getStatusIcon() + "] " + getTask() + "\n");
    }
    /**
     * Indicate task not done
     */
    public void unmark() {
        isDone = false;
        //System.out.println("OK, I've marked this task as not done yet:\n [" + getStatusIcon() + "] " + getTask() + "\n");
    }
    /**
     * Display to text file
     */
    public String toOutput() {
        return (description);
    }
    /**
     * For output when list is called
     */
    public String toString() {

        return "[" + getStatusIcon() + "] " + description;
    }
}
