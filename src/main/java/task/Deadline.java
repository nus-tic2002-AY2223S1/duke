package task;
public class Deadline extends Task{

    private String atDate;

    public Deadline(String taskName, String atDate) {
        super(taskName);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + isMarkAsDone() + "] " + TaskName + " (by: " + atDate + ")";
    }
}
