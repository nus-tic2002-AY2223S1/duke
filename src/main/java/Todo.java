public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        return this.description;
    }
    public String getDateAndTime() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
