package tasklist;

public class Event extends Task {
    private String at;

    public Event(String description, String at){
        super(description);
        super.typeTask = "E";
        this.at = at;
    }

    public String getString(){
        return super.getString() + "(at: " + this.at + ")";
    }
    public String getTask(){
        return this.typeTask + " | " + (super.isDone ? "1" : "0") + " | " + super.getDescription() + " | " + this.at;
    }
    public String getAt(){
        return this.at;
    }
    public boolean matchDue(String duedate){
        return this.at.contains(duedate);
    }
    public void setDate(String newdate){
        this.at = newdate;
    }

}