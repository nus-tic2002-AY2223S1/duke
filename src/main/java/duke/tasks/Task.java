package duke.tasks;

import duke.exceptions.DukeException;

public class Task {

    protected StringBuilder description;
    protected boolean isDone;
    protected int taskNo;

    public Task(String d) throws DukeException {
        this.description = new StringBuilder(d);
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setTaskNo(int taskId) {
        taskNo = taskId;
    }

    public int getTaskNo() {
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
