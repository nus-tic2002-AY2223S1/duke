package duke.impl;

import java.util.Scanner;

/**
 * THe Parser module to parse user input and identify the commands specified.
 */
public class Parser {
    protected String[] in;

    /**
     * Calls the corresponding implementation based on Command type.
     */
    public Parser() {
    }

    /**
     * Reads input from CLI.
     * Line breaks by carriage return.
     * Input will be tokenized, separated by spaces.
     *
     * @return Parser Object of read-in string
     */
    public Parser readIn() {
        Scanner inputRaw = new Scanner(System.in).useDelimiter("\n");
        String inputLine = inputRaw.nextLine();
        this.in = inputLine.split(" ", 2);
        return this;
    }

    /**
     * Calls the corresponding implementation based on input args.
     * Line breaks by carriage return.
     * Input will be tokenized, separated by spaces.
     *
     * @param text Input string
     * @return Parser Object of read-in string
     */
    public Parser readInText(String text) {
        this.in = text.split(" ", 2);
        return this;
    }

    /**
     * Directly injects read-in string.
     *
     * @param in Input to be injected
     * @return Parser Object of read-in string
     */
    public Parser injectIn(String[] in) {
        this.in = in;
        return this;
    }

    /**
     * Parses input and identify the corresponding commands.
     *
     * @return Cmd Object of parsed command input.
     */
    public Cmd parse() {
        switch (this.in[0].trim()) {
        case "list":
            return new Cmd(Cmd.CmdTypes.PRINT_LIST, null);
        case "mark":
            return new Cmd(Cmd.CmdTypes.MARK_TASK, this.in);
        case "unmark":
            return new Cmd(Cmd.CmdTypes.UNMARK_TASK, this.in);
        case "todo":
            return new Cmd(Cmd.CmdTypes.ADD_TODO, this.in);
        case "deadline":
            return new Cmd(Cmd.CmdTypes.ADD_DEADLINE, this.in);
        case "event":
            return new Cmd(Cmd.CmdTypes.ADD_EVENT, this.in);
        case "delete":
            return new Cmd(Cmd.CmdTypes.DELETE_TASK, this.in);
        case "bye":
            return new Cmd(Cmd.CmdTypes.EXIT, null);
        case "day":
            return new Cmd(Cmd.CmdTypes.FIND_DATE, this.in);
        case "find":
            return new Cmd(Cmd.CmdTypes.FIND_TASK, this.in);
        case "archive":
            return new Cmd(Cmd.CmdTypes.ARCHIVE, this.in);
        case "restore":
            return new Cmd(Cmd.CmdTypes.RESTORE, this.in);
        case "":
            return new Cmd(Cmd.CmdTypes.RETURN, null);
        default:
            return new Cmd(Cmd.CmdTypes.UNKNOWN_CMD, null);
        }
    }
}
