package entity;

import parser.Parser;
import util.PrintUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return Parser.parseDateTimeToString(deadline);
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String getDetails() {
        return String.format("[%s]%s %s (by: %s)", getTypeIcon(), getStatusIcon(), getDescription(), getDeadline());
    }

    @Override
    public String getSavingFormatDetails() {
        return String.format("%s | %d | %s | %s\n", getTypeIcon(), isDone() ? 1 : 0, getDescription(), getDeadline());
    }
}
