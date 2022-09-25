public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String getTask() {
        if(status) {
            return "[T][X] " + name;
        }
        else {
            return "[T][ ] " + name;
        }
    }
}
