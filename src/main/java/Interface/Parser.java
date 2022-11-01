package Interface;

import java.util.Scanner;

public class Parser {
    protected String[] in;
    public Parser() {}
    public Parser readIn() {
        Scanner inputRaw = new Scanner(System.in).useDelimiter("\n");
        String inputLine = inputRaw.nextLine();
        this.in = inputLine.split(" ", 2);
        return this;
    }
    public Parser injectIn(String[] in){
        this.in = in;
        return this;
    }
    public Cmd parse(){
        switch (this.in[0]){
                case "list":
                    return new Cmd(Cmd.CmdTypes.PRINT_LIST,null );
                case "mark":
                    return new Cmd(Cmd.CmdTypes.MARK_TASK,this.in);
                case "unmark":
                    return new Cmd(Cmd.CmdTypes.UNMARK_TASK,this.in);
                case "todo":
                    return new Cmd(Cmd.CmdTypes.ADD_TODO,this.in);
                case "deadline":
                    return new Cmd(Cmd.CmdTypes.ADD_DEADLINE,this.in);
                case "event":
                    return new Cmd(Cmd.CmdTypes.ADD_EVENT,this.in);
                case "delete":
                    return new Cmd(Cmd.CmdTypes.DELETE_TASK,this.in);
                case "bye":
                    return new Cmd(Cmd.CmdTypes.EXIT,null);
                case "day":
                    return new Cmd(Cmd.CmdTypes.FIND_DATE,this.in);
                case "find":
                    return new Cmd(Cmd.CmdTypes.FIND_TASK,this.in);
                case "view":
                    return new Cmd(Cmd.CmdTypes.VIEW_SCHEDULE,this.in);
                case "":
                    return new Cmd(Cmd.CmdTypes.RETURN,null);
                default:
                    return new Cmd(Cmd.CmdTypes.UNKNOWN_CMD,null);
            }
    }
}
