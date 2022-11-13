package Duke.TaskList;

public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveToFile() {
        int status = isDone ? 1:0;
        return "T | " + status + " | " + super.description + "\r";
    }
}
