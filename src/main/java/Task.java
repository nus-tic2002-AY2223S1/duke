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

    public void markAsDone() {
        this.isDone = true;
        print("Nice! I've marked this task as done:\n");
        print("       " + this.toString() + "\n");
    }

    public void markAsUndone() {
        this.isDone = false;
        print("OK, I've marked this task as not done yet:\n");
        print("       " + this.toString() + "\n");
    }

    public void printTask(){
        System.out.print(this.toString() + "\n");
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public static void print(String toPrint){
        String indentFive = "     ";
        System.out.print(indentFive + toPrint);
    }
    //...
}

