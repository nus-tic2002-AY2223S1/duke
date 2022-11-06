package Entity;

import jdk.jshell.spi.ExecutionControl;

public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;
    static int taskCount =0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount ++;
    }

    public Task(String description, String type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void updateStatus(boolean isDone){
        this.isDone = isDone;
    }

    public String toDisk() {
        return type + " | " + ((isDone) ? "1" : "0") + " | " + description;
    }

    public void print(){
//        System.out.println("print task");
        String icon = String.format("\t[%s] ",this.getStatusIcon());
        System.out.println(icon + description);
    }


}
