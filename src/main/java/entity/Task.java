package entity;

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
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getTypeIcon() {
        return " ";
    }

    public String getDetails() {
        return String.format("[%s]%s %s", getTypeIcon(), getStatusIcon(), getDescription());
    }

    public String getSavingFormatDetails() {
        return String.format("%s | %d | %s\n", getTypeIcon(), isDone ? 1 : 0, getDescription());
    }
}
