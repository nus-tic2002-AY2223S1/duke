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
    public String run(ArrayList<Task> tasks) throws DukeException {
        arrayList = tasks;
        this.r = new Runner(arrayList);
        switch (this.t){
            case RETURN:
                break;
            case UNKNOWN_CMD:
                return r.printCommandUnknownMessage();
            case PRINT_LIST:
                return r.printList(true);
            case MARK_TASK:
                return r.processAction(Runner.Action.MARK,input);
            case UNMARK_TASK:
                return r.processAction(Runner.Action.UNMARK,input);
            case ADD_TODO:
                return r.processAddTask(Runner.Command.TODO,input);
            case ADD_DEADLINE:
                return r.processAddTask(Runner.Command.DEADLINE,input);
            case ADD_EVENT:
                return r.processAddTask(Runner.Command.EVENT,input);
            case DELETE_TASK:
                return r.processAction(Runner.Action.DELETE,input);
            case FIND_DATE:
                return r.processFindDate(input);
            case FIND_TASK:
                return r.processFindTask(input);
//            case VIEW_SCHEDULE:
//                return r.processViewSchedule(input);
            case ARCHIVE:
                return r.processArchive();
            case RESTORE:
                //isExit
                return r.processRestore(input);
            case EXIT:
                this.isExit = true;
                return r.printExitMessage();
        }
        return "";
    }
}
