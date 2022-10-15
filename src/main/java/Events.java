public class Events extends Task{

    protected String due;
    public Events(String description, String due) {
        super(description);
        this.due = due;
    }
    public String getDescription() {
        String mark = this.getStatus() ? "[X]" : "[ ]";
        return "[D]" + mark + " " + description + " (" + due + ")";
    }
}
