package service;

import constant.CommandEnum;
import service.command.AddTaskCommand;
import service.command.Command;
import service.command.ExitCommand;
import service.command.ListTaskCommand;
import service.command.MarkTaskCommand;
import service.command.ShowCommand;
import service.command.UnmarkTaskCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    /**
     * mapping for `commandName` to `commandInstance`
     */
    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("show_command", ShowCommand.getInstance());
        commandMap.put("list", ListTaskCommand.getInstance());
        commandMap.put("mark", MarkTaskCommand.getInstance());
        commandMap.put("unmark", UnmarkTaskCommand.getInstance());
        commandMap.put("bye", ExitCommand.getInstance());
    }

    private CommandManager() {}

    /**
     * @description get command instance by given command name in runtime
     * @author Dex
     * @date 2022/08/31
     * @param commandName: name of command
     */
    public static Command getCommand(String commandName) {
        Command command = commandMap.get(commandName);
        // add task if commandName is not found in the command list (level 2 implementation)
        if (command == null) {
            return AddTaskCommand.getInstance();
        }
        return command;
    }
}
