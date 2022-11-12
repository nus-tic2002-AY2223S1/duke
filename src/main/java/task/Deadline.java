package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * a task that has end date
 */
public class Deadline extends Task implements TaskInterface {
    LocalDateTime endDate;
    public Deadline(String work, LocalDateTime endDate) {
        super(work);
        this.endDate = endDate;
    }

    public Deadline(boolean isDone, String work, long id, LocalDateTime endDate) {
        super(isDone, work, id);
        this.endDate = endDate;
    }

    @Override
    public String getString() {
        return "[D]" + super.toString() + "(by: "+ endDate.format(DateTimeFormatter.ofPattern("MMM d hh:mma")) +")";
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
        return endDateInString();
    }

    private String endDateInString() {
        return endDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }
}
