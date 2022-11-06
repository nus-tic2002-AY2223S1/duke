package task;

import java.util.Date;

/**
 * A parent class for all the class with common feature like isDone, id, and work
 */
public class Task {
    private boolean isDone;
    private String work;
    private long id;

    protected Task(boolean isDone, String work) {
        Date date = new Date();
        this.isDone = isDone;
        this.work = work;
        id = date.getTime();
    }

    protected Task(boolean isDone, String work, long id) {
        this.isDone = isDone;
        this.work = work;
        this.id = id;
    }

    protected Task(String work) {
        this(false, work);
    }

    protected void markTask(boolean mark) {
        isDone = mark;
    }

    protected String getWork() {
        return work;
    }

    protected boolean isDone() {
        return isDone;
    }

    protected long getId() { return id; }
    @Override
    public String toString() {
        String value = isDone ? "[X]" : "[ ]";
        value += work;
        return value;
    }
}

