package entity;

import parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime time;
//    private String endTime;

    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public Event(String name, LocalDateTime time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    public String getTime() {
        return Parser.parseDateTimeToString(time);
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String getDetails() {
        return String.format("[%s]%s %s (at: %s)", getTypeIcon(), getStatusIcon(), getDescription(), getTime());
    }

    @Override
    public String getSavingFormatDetails() {
        return String.format("%s | %d | %s | %s\n", getTypeIcon(), isDone() ? 1 : 0, getDescription(), getTime());
    }
}
