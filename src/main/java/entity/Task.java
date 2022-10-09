package entity;

import util.PrintUtil;

public class Task {

    private String description = "";
    private boolean isDone = false;

    public Task(String name) {
        this.description = name.trim();
    }

    public Task(String name, boolean isDone) {
        this.description = name.trim();
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateStatus() {
        this.isDone = !isDone;

        if (isDone) {
            PrintUtil.printWithIndentation("Nice! I've marked this task as done:\n\t   " + getDetails());
        } else {
            PrintUtil.printWithIndentation("OK, I've marked this task as not done yet:\n\t   " + getDetails());
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getTypeIcon() {
        return "[ ]";
    }

    public String getDetails() {
        return String.format("%s%s %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
}
