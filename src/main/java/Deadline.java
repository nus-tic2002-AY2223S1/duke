public class Deadline extends ToDo {
    protected String my_by;

    public Deadline(String description, String by) {
        super(description);
        my_by = by;
    }

    public void setDeadline(String date) {
        my_by = date;
    }

    public String getDeadline() {
        return my_by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + my_by + ")";
    }
}