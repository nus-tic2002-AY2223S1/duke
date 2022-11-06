package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * a task that has date with start and end time
 */
public class Event extends Task implements TaskInterface {
    LocalDateTime startDate;
    LocalDateTime endDate;

    public Event(String work, LocalDateTime startDate, LocalDateTime endDate) {
        super(work);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(boolean isDone, String work, long id, LocalDateTime startDate, LocalDateTime endDate) {
        super(isDone, work, id);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getString() {
        return  "[E]" + super.toString() + "(at: "+formatDate()+")";
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

    private String formatDate() {
        String date = startDate.format(DateTimeFormatter.ofPattern("MMM d HH:mm"));
        date = date + "-" + endDate.format(DateTimeFormatter.ofPattern("HH:mm"));
        return date;
    }

    private String endDateInString() {
        return startDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) +"-"+ endDate.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
