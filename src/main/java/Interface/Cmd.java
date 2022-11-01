package Interface;

import Duke.Task;
import Util.DukeException;

import java.util.ArrayList;

public class Cmd {
    protected boolean isExit;
    public enum CmdTypes{
        UNKNOWN_CMD,
        PRINT_LIST,
        MARK_TASK,
        UNMARK_TASK,
        ADD_TODO,
        ADD_DEADLINE,
        ADD_EVENT,
        DELETE_TASK,
        FIND_DATE,
        FIND_TASK,
        VIEW_SCHEDULE,
        ARCHIVE,
        RESTORE,
        RETURN,
        EXIT;
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
    public void run(ArrayList<Task> tasks) throws DukeException {
        arrayList = tasks;
        this.r = new Runner(arrayList);
        switch (this.t){
            case RETURN:
                break;
            case UNKNOWN_CMD:
                r.printCommandUnknownMessage();
                break;
            case PRINT_LIST:
                r.printList(true);
                break;
            case MARK_TASK:
                r.processAction(Runner.Action.MARK,input);
                break;
            case UNMARK_TASK:
                r.processAction(Runner.Action.UNMARK,input);
                break;
            case ADD_TODO:
                r.processAddTask(Runner.Command.TODO,input);
                break;
            case ADD_DEADLINE:
                r.processAddTask(Runner.Command.DEADLINE,input);
                break;
            case ADD_EVENT:
                r.processAddTask(Runner.Command.EVENT,input);
                break;
            case DELETE_TASK:
                r.processAction(Runner.Action.DELETE,input);
                break;
            case FIND_DATE:
                r.processFindDate(input);
                break;
            case FIND_TASK:
                r.processFindTask(input);
                break;
            case VIEW_SCHEDULE:
                r.processViewSchedule(input);
                break;
            case ARCHIVE:
                r.processArchive();
                break;
            case RESTORE:
                this.isExit = r.processRestore(input);
                break;
            case EXIT:
                r.printExitMessage();
                this.isExit = true;
                break;
        }
    }
}
