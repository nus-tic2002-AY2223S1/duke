public class Task {
    private boolean isDone;
    private String work;

    public Task(boolean isDone, String work) {
        this.isDone = isDone;
        this.work = work;
    }

    public Task(String work) {
        this(false, work);
    }

    public void markTask(boolean mark) {
        isDone = mark;
    }

    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        String value = isDone ? "[X]" : "[ ]";
        value += " " + work;
        return value;
    }

}
