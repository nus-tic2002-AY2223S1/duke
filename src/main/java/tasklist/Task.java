package tasklist;

public class Task {
    private String description;
    public Boolean isDone;
    public String typeTask;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.typeTask = " ";
    }

    public String getDescription(){
        return this.description;
    }
    public String getDone(){
        return (this.isDone ? "X" : " ");
    }
    public String getTypeTask(){ return this.typeTask; }
    public String getString(){
        return "";
    }
    public String getBy(){
        return "";
    }
    public String getAt(){
        return "";
    }
    public String getTask() { return ""; }
    public void markDone(){
        this.isDone = true;
    }
    public void unmarkDone(){
        this.isDone = false;
    }
    public boolean matchFind(String find){
        return this.description.contains(find);
    }
    public boolean matchDue(String duedate){ return false; }
    public void setDate(String newdate){ }

}