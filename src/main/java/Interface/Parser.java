package Interface;
public class Parser {
    static Ui ui = new Ui();

    public Parser() {}
    public static Cmd parse(String[] processedInput){
    switch (processedInput[0]){
                case "list":
                    return new Cmd(Cmd.CmdTypes.PRINT_LIST,null );
                case "mark":
                    return new Cmd(Cmd.CmdTypes.MARK_TASK,processedInput);
                case "unmark":
                    return new Cmd(Cmd.CmdTypes.UNMARK_TASK,processedInput);
                case "todo":
                    return new Cmd(Cmd.CmdTypes.ADD_TODO,processedInput);
                case "deadline":
                    return new Cmd(Cmd.CmdTypes.ADD_DEADLINE,processedInput);
                case "event":
                    return new Cmd(Cmd.CmdTypes.ADD_EVENT,processedInput);
                case "delete":
                    return new Cmd(Cmd.CmdTypes.DELETE_TASK,processedInput);
                case "bye":
                    return new Cmd(Cmd.CmdTypes.EXIT,null);
                case "":
                    break;
                default:
                    return new Cmd(Cmd.CmdTypes.UNKNOWN_CMD,null);
            }
        return null;
    }

}
