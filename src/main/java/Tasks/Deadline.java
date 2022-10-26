package Tasks;

public class Deadline extends Task implements TaskInterface {
    public Deadline(String work) {
        super(work);
    }

    public Deadline(boolean isDone, String work, long id) {
        super(isDone, work, id);
    }

    @Override
    public String getString() {
        return "[D]" + super.toString();
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
}
