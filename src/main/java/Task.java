public class Task {
    protected String description;
    protected boolean isDone;
    

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTask(){
        return description;
    }

    public boolean mark(){
        return isDone=true;
    }
    public boolean unmark(){
        return isDone=false;
    }
    public String toString(){
	return ("["+getStatusIcon()+"] "+description);	
    }
    public String encapTask(String t, int count){
	return ("Got it. I've added this task:\n "+ t +"Now you have "+count+" tasks in the list.\n");
    }
}