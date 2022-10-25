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
    public String getTaskDetails(){ return ""; }

    @Override
    public String getDescription(String userInput){ return "";}
    @Override
    public String getDateInStr(String userInput) { return ""; }
}
