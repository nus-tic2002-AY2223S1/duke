package tasklist;

public class Todo extends Task {

    public Todo(String description){
        super(description);
        super.typeTask = "T";
    }
    public String getTask(){
        return this.typeTask + " | " + (super.isDone ? "1" : "0") + " | " + super.getDescription();
    }

}