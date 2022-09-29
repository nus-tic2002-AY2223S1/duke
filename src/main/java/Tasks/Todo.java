package Tasks;

public class Todo extends Task implements TaskInterface {

    public Todo(String work) {
        super(work);
    }

    @Override
    public String getString() {
        return "[T]" + super.toString();
    }
    @Override
    public boolean isDone() {
        return super.isDone();
    }

    @Override
    public void markTask(boolean mark){
        super.markTask(mark);
    }
}
