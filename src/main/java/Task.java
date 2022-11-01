public abstract class Task {
    protected String name;
    protected boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void setStatus (boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String getTask();
}
