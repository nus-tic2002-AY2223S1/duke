package model;

public class Todo extends Task {

    public Todo(String work) {
        super(work);
    }

    @Override
    public boolean isDone() {
        return super.isDone();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}