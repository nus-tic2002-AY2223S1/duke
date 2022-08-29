package exercise.week3;

public class Todo extends Task {

    private boolean isDone;

    public Todo(String description) {
        super(description);
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String s1 = super.toString();
        String s2 = isDone() ? "Yes" : "No";
        return s1 + System.lineSeparator() + "is done? " + s2;
    }
}
