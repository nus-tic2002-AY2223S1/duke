package entity;

public class Task {

    protected String name;

    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
