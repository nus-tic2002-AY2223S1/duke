public class Deadlines extends Task{
    protected String due;
    public Deadlines(String description, String due) {
        super(description);
        this.due = due;
    }
    public String getDescription() {
        String mark = this.getStatus() ? "[X]" : "[ ]";
        return "[D]" + mark + " " + description + " (" + due + ")";
    }
}