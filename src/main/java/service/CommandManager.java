package service;

import service.command.AddCommand;
import service.command.Command;
import service.command.ExitCommand;
import service.command.ListCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("list", ListCommand.getInstance());
        // commandMap.put("mark", MarkCommand.getInstance());
        // commandMap.put("unmark", UnmarkCommand.getInstance());
        commandMap.put("bye", ExitCommand.getInstance());
    }

    private CommandManager() {}

    public static Command getCommand(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            return AddCommand.getInstance();
        }
        return command;
    }
}
