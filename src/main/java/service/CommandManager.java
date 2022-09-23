package service;

import service.command.AddDeadlineCommand;
import service.command.AddEventCommand;
import service.command.AddTodoCommand;
import service.command.Command;
import service.command.DeleteTaskCommand;
import service.command.ExitCommand;
import service.command.FindTaskCommand;
import service.command.ListTaskCommand;
import service.command.MarkTaskCommand;
import service.command.RescheduleTaskCommand;
import service.command.ShowCommand;
import service.command.UndefinedCommand;
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
        commandMap.put("delete", DeleteTaskCommand.getInstance());
        commandMap.put("find", FindTaskCommand.getInstance());
        commandMap.put("reschedule", RescheduleTaskCommand.getInstance());
        commandMap.put("todo", AddTodoCommand.getInstance());
        commandMap.put("deadline", AddDeadlineCommand.getInstance());
        commandMap.put("event", AddEventCommand.getInstance());
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
        return commandMap.getOrDefault(commandName, UndefinedCommand.getInstance());
    }
}
