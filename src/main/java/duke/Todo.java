package duke;
public class Todo extends Task{
    public Todo(String description) {
        super(description);
        assert this.description.equals("");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFile() { return("T | "+(super.isDone? 1:0)+" | "+super.description+"\n");}
}
