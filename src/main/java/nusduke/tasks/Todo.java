package nusduke.tasks;

public class Todo extends Task {
    protected boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    public Todo(String d) {
        super(d);
        isDone = false;
    }
};
