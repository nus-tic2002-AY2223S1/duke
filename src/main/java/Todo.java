public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public String getDescription() {
        String mark = this.getStatus() ? "[X]" : "[ ]";
        return "[T]" + mark + " " + description;
    }
}