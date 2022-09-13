public class ToDo extends Task {


    public ToDo(String description, String Tasktype) {
        super(description, Tasktype);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}