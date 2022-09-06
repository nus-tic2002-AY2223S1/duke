
package task;
public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol = "*";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        // Return tick when done, or 'X' symbol when it is not done
        return (isDone ? "\u2713" : "\u2718");
    }
    public String getTypeIcon() {
        return symbol;
    }

    public String getDescription() {
        return this.description;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsnotDone() {
        this.isDone = false;
    }
    public String toString() {
        return String.format("[%s][%s] %s", this.getTypeIcon(), this.getStatusIcon(), this.getDescription());
    }
}
