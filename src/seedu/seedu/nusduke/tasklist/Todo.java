package nusduke.tasklist;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        return super.getDescription();
    }
    public String getDateAndTime() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}