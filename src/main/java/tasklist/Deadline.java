package tasklist;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by){
        super(description);
        super.typeTask = "D";
        this.by = by;
    }

    public String getString(){
        return super.getString() + "(by: " + this.by + ")";
    }
    public String getTask(){
        return this.typeTask + " | " + (super.isDone ? "1" : "0") + " | " + super.getDescription() + " | " + this.by;
    }
    public String getBy(){
        return this.by;
    }
    public boolean matchDue(String duedate){
        return this.by.contains(duedate);
    }
    public void setDate(String newdate){
        this.by = newdate;
    }

}