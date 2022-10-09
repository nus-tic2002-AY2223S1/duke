package entity;

import util.PrintUtil;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline.trim();
    }

    public Deadline(String name, String deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
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
