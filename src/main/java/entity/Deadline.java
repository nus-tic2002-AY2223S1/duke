package entity;

import constant.Constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description) {
        super(description);
        setType(Constant.Task.TYPE_DEADLINE);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        setType(Constant.Task.TYPE_DEADLINE);
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getDisplayTime());
    }

    private String getDisplayTime() {
        return by.format(DateTimeFormatter.ofPattern(Constant.Time.DISPLAY_FORMAT));
    }
}
