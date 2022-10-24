public class Deadline extends Task{

    private String atDate;

    public Deadline(String taskName, String atDate) {
        super(taskName);
        this.atDate = atDate;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + atDate + ")";
    }
}
