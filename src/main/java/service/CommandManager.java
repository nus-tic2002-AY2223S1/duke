package service;

import entity.Form;
import exception.CommandNotFoundException;
import service.Parser;
import service.command.Command;
import service.command.ExitCommand;
import service.command.ListCommand;
import service.command.MarkCommand;
import service.command.UnmarkCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    // make the instance single
    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("list", ListCommand.getInstance());
        commandMap.put("mark", MarkCommand.getInstance());
        commandMap.put("unmark", UnmarkCommand.getInstance());
        commandMap.put("exit", ExitCommand.getInstance());
    }

    private CommandManager() {}

    public static Command getCommand(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            String errorMsg = String.format("commandName: %s is not found!%n", commandName);
            throw new CommandNotFoundException(errorMsg);
        }
        return command;
    }
}
