package domain.task;

import exceptions.EmptyDescriptionException;

import static utils.TaskUtil.isNullOrEmpty;

public class Task {
    private static int count = 0;
    protected int index;
    protected String description;
    protected boolean isDone;

    public Task(String description) throws EmptyDescriptionException {
        if (isNullOrEmpty(description))
            throw new EmptyDescriptionException();
        this.description = description;
        this.isDone = false;
        count++;
        index = count;
    }

    public static int getCount() {
        return count;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
