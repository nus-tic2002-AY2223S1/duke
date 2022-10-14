package duke.service;

import duke.service.command.DeleteTaskCommand;
import duke.service.command.ShowCommand;
import duke.service.command.UndefinedCommand;
import duke.service.command.AddDeadlineCommand;
import duke.service.command.AddEventCommand;
import duke.service.command.AddTodoCommand;
import duke.service.command.Command;
import duke.service.command.ExitCommand;
import duke.service.command.FindTaskCommand;
import duke.service.command.ListTaskCommand;
import duke.service.command.MarkTaskCommand;
import duke.service.command.RescheduleTaskCommand;
import duke.service.command.UnmarkTaskCommand;

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
