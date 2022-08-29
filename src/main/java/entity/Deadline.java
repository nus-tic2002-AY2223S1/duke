package entity;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private String by;

    private LocalDateTime time;

    public Deadline(String description) {
        super(description);
        super.type = "D";
    }

    public Deadline(String description, String by, LocalDateTime time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
