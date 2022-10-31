public class Task {

    // properties
    protected String description;
    protected boolean isDone;

    // constructors
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // methods
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (isDone()) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

}
