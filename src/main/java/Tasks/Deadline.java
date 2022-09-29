package Tasks;

public class Deadline extends Task implements TaskInterface {

    public Deadline(String work) {
        super(work);
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
}
