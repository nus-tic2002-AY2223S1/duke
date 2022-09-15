import jdk.jshell.spi.ExecutionControl;

public class Task {
    protected String description;
    protected boolean isDone;
    static int taskCount =0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount ++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void updateStatus(boolean isDone){
        this.isDone = isDone;
        if(isDone){
//            System.out.println(line);
            System.out.println("\tNice! I've marked this task as done:");
        }else {
            System.out.println("\tOK, I've marked this task as not done yet:");
        }
        this.print();
    }

    public void print(){
        String icon = String.format("\t[%s] ",this.getStatusIcon());
        System.out.println(icon + description);
    }


}
