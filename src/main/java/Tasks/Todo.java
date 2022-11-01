package Tasks;

/**
 * A basic task
 */
public class Todo extends Task implements TaskInterface {

    public Todo(String work) {
        super(work);
    }

    public Todo(boolean isDone, String work) { super(isDone, work); }

    public Todo(boolean isDone, String work, long id) {
        super(isDone, work, id);
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

    @Override
    public String getWork() {
        return super.getWork();
    }

    @Override
    public long getID() {
        return super.getId();
    }

    @Override
    public String getEndDate() {
        return "";
    }
}
