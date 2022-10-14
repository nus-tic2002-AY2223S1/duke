package nus.duke;

public class Todo extends Task {

    public Todo(String wish) {
        super(wish);
    }

    @Override
    public String getTaskType(){
        return "T";
    }
}
