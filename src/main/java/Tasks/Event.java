package Tasks;

import java.time.LocalDate;

public class Event extends Task implements TaskInterface {
    String endDate;

    public Event(String work, String endDate) {
        super(work);
        this.endDate = endDate;
    }

    public Event(boolean isDone, String work, long id, String endDate) {
        super(isDone, work, id);
        this.endDate = endDate;
    }

    @Override
    public String getString() {
        return  "[E]" + super.toString() + " (at: "+endDate+")";
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
        return endDate;
    }
}
