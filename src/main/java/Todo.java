public class Todo{
    private final String TaskName;
    private final boolean MarkAsDone;

    public Todo(String taskName, boolean markAsDone) {
        this.TaskName = taskName;
        this.MarkAsDone = markAsDone;
    }

    public void printOneTuple(){
        String Tick;
        if (this.MarkAsDone){
            Tick = "x";
        }
        else {
            Tick = " ";
        }
        System.out.println("[" + Tick + "] " + this.TaskName);
    }

}
