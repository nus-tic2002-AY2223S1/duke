package duke.tasks;

public class Task {
    protected StringBuilder description;
    protected boolean isDone;
    protected int taskNo = 0;

    public Task(String d) {
        this.description = new StringBuilder(d);
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setTaskno(int taskNo) {
        this.taskNo = taskNo;
    }

    public int getTaskno() {
        return taskNo;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return description.toString();
    }
}
