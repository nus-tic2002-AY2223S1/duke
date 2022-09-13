public class Event extends Task {
    protected String my_by;


    public Event(String description, String taskType, String by) {
        super(description, taskType);
        this.my_by = by;
    }

    public void setEvent(String date) {
        my_by = date;
    }

    public String getEvent() {
        return my_by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + my_by + ")";
    }
}
