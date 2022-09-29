package Tasks;

public class Event extends Task implements TaskInterface {
    public Event(String work) {
        super(work);
    }

    @Override
    public String getString() {
        return  "[E]" + super.toString();
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
