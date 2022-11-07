package duke.impl;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * The middleware between Command Parser and Logic Implementations.
 */
public class Cmd {
    /**
     * Types of pre-defined Commands.
     */
    public enum CmdTypes {
        UNKNOWN_CMD, PRINT_LIST, MARK_TASK, UNMARK_TASK, ADD_TODO, ADD_DEADLINE, ADD_EVENT, DELETE_TASK, FIND_DATE,
        FIND_TASK, ARCHIVE, RESTORE, RETURN, HELP, EXIT
    }

    protected CmdTypes t;
    protected String[] input;
    protected Runner r;
    protected ArrayList<Task> arrayList;

    /**
     * Initialize the Cmd Object with CmdTypes and parsed input.
     *
     * @param c              The CmdType
     * @param processedInput The parsed user input string
     */
    public Cmd(CmdTypes c, String[] processedInput) {
        this.t = c;
        this.input = processedInput;
    }

    /**
     * Calls the corresponding logic implementation based on the input Command type.
     * The tasks argument is mandatory, but is not required to be populated. i.e. empty list is acceptable.
     * If tasks is not empty, command will be processed with existing tasks in mind and vice-versa.
     * The locale argument is mandatory,and can be either Ui.LocaleRegion.En or Ui.LocaleRegion.Cn
     * The language locale of Duke's reply will rely on this argument.
     * <p>
     * This method has a direct dependency on the Runner Class, which will be constructed upon initialization.
     *
     * @param tasks  An array of Tasks in ArrayList type to be processed.
     * @param locale The Ui.LocaleRegion Enum to determine reply language.
     * @return Message to display in GUI after processing commands.
     */
    public String run(ArrayList<Task> tasks, Ui.LocaleRegion locale) {
        arrayList = tasks;
        this.r = new Runner(arrayList, locale);
        switch (this.t) {
        case RETURN:
            break;
        case UNKNOWN_CMD:
            return r.printCommandUnknownMessage();
        case PRINT_LIST:
            return r.printList(true);
        case MARK_TASK:
            return r.processAction(Runner.Action.MARK, input);
        case UNMARK_TASK:
            return r.processAction(Runner.Action.UNMARK, input);
        case ADD_TODO:
            return r.processAddTask(Runner.Command.TODO, input);
        case ADD_DEADLINE:
            return r.processAddTask(Runner.Command.DEADLINE, input);
        case ADD_EVENT:
            return r.processAddTask(Runner.Command.EVENT, input);
        case DELETE_TASK:
            return r.processAction(Runner.Action.DELETE, input);
        case FIND_DATE:
            return r.processFindDate(input);
        case FIND_TASK:
            return r.processFindTask(input);
        case ARCHIVE:
            return r.processArchive();
        case RESTORE:
            return r.processRestore(input);
        case HELP:
            return r.printHelpMessage();
        case EXIT:
            return r.printExitMessage();
        default:
            return "";
        }
        return "";
    }

    /**
     * Retrieves the input.
     *
     * @return An array of input
     */
    public String[] getInput() {
        return this.input;
    }

    /**
     * Retrieves the CmdTypes enum.
     *
     * @return Cmd of CmdTypes enum
     */
    public CmdTypes getCmdTypes() {
        return this.t;
    }
}
