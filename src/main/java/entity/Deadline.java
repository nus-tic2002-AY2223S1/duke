package entity;

import util.PrintUtil;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline.trim();
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getDetails() {
        return String.format("%s%s %s (%s)", getTypeIcon(), getStatusIcon(), getDescription(), getDeadline());
    }
}
