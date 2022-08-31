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
import java.util.Objects;

public class CommandManager {

    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("show_command", ShowCommand.getInstance());
        commandMap.put("list", ListTaskCommand.getInstance());
        commandMap.put("mark", MarkTaskCommand.getInstance());
        commandMap.put("unmark", UnmarkTaskCommand.getInstance());
        commandMap.put("bye", ExitCommand.getInstance());
    }

    private CommandManager() {}

    public static Command getCommand(String commandName) {
        Command command = commandMap.get(commandName);
        // add task if commandName is not found in the command list (level 2 implementation)
        if (command == null) {
            return AddTaskCommand.getInstance();
        }
        return command;
    }
}
