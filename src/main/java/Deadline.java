public class Deadline extends Task {
    protected String due;

    public Deadline(String name, String due) {
        super(name);
        this.due = due;
    }

    @Override
    public String getTask() {
        if(status) {
            return "[D][X] " + name + "(by: " + due + ")";
        }
        else {
            return "[D][ ] " + name  + "(by: " + due + ")";
        }
    }
}
