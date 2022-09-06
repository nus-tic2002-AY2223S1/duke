package entity;

import constant.Constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description) {
        super(description);
        setType(Constant.Task.TYPE_EVENT);
    }

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        setType(Constant.Task.TYPE_EVENT);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), getDisplayTime());
    }

    private String getDisplayTime() {
        String begin = startTime.format(DateTimeFormatter.ofPattern(Constant.Time.DISPLAY_FORMAT));
        String end = endTime.format(DateTimeFormatter.ofPattern(Constant.Time.DISPLAY_FORMAT));
        return String.format("%s - %s", begin, end);
    }
}
