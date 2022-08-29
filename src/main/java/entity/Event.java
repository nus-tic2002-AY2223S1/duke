package entity;

import java.time.LocalDateTime;

public class Event extends Task {

    private String time;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description) {
        super(description);
        super.type = "E";
    }

    public Event(String description, String time, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
