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
            System.out.println("\tNice! I've marked this task as done:\n");
        }else {
            System.out.println("\tOK, I've marked this task as not done yet:\n");
        }
        this.print();
    }

    public void print(){
        String icon = String.format("[%s] ",this.getStatusIcon());
        System.out.println("\t" + icon + description);
    }


}
