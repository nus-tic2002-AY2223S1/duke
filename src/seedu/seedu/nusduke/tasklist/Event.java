package nusduke.tasklist;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public String getDescription() {
        return super.getDescription();
    }
    public String getDateAndTime() {
        return this.at;
    }
    @Override
    public String toString() {
        String[] parts = this.at.split(" ", 2);
        return "[E]" +  super.toString() + " (" + parts[0] + ": " + parts[1] + ")";
    }

}