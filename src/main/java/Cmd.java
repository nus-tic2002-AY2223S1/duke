import java.util.ArrayList;

public class Cmd {
    protected boolean isExit;
    public enum CmdTypes{
        PRINT_LIST,
        MARK_TASK,
        UNMARK_TASK,
        ADD_TODO,
        ADD_DEADLINE,
        ADD_EVENT,
        DELETE_TASK;
    }
    CmdTypes t;
    Runner r;
    String[] input;
    protected ArrayList<Task> arrayList;
    public Cmd(CmdTypes c,String[] processedInput) {
        this.t = c;
        this.input = processedInput;

    }

    public boolean isExit(){
        return this.isExit;
    }
    public void run(ArrayList<Task> tasks, Ui runtimeUi) throws DukeException{
        arrayList = tasks;
        this.r = new Runner(arrayList);
        switch (this.t){
            case PRINT_LIST:
                r.printList(true);
            case MARK_TASK:
                r.processAction(Runner.Action.MARK,this.input);
                break;
            case UNMARK_TASK:
                r.processAction(Runner.Action.UNMARK,this.input);
                break;
            case ADD_TODO:
                r.processCommand(Runner.Command.TODO,this.input);
                break;
            case ADD_DEADLINE:
                r.processCommand(Runner.Command.DEADLINE,this.input);
                break;
            case ADD_EVENT:
                r.processCommand(Runner.Command.EVENT,this.input);
                break;
            case DELETE_TASK:
                r.processAction(Runner.Action.DELETE,this.input);
                break;
        }
    }


}
