package nus.duke.task;

public class Todo extends Task {

    public Todo(String userInput) {
        super(userInput);
    }

    @Override
    public String getTaskType(){
        return "T";
    }

    @Override
    public String getTaskDetails(){
        return "";
    }
}
