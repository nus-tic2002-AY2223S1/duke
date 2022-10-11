
import java.util.*;

import Utils.DukeUtils;
import constant.CommandExecutor;
import entity.TaskList;
import exceptions.DukeException;

public class Duke {
    private static final Map<String, CommandExecutor> COMMAND_EXECUTOR_MAP = createMap();
    public static void main(String[] args) {
        // DUKE greeting message
        DukeUtils.dukeInit();
        TaskList taskList = new TaskList();
        String inputs, commandHead;
        CommandExecutor command;
        whileLoop:
        while (true) {
            inputs = inputText();
            commandHead = DukeUtils.getCommandHead(inputs);
            command = COMMAND_EXECUTOR_MAP.get(commandHead);
            command = command != null ? command : CommandExecutor.UNDEFINED;
            if (command == CommandExecutor.BYE) break whileLoop;

            try {
                command.execute(taskList, inputs);
            } catch (DukeException err) {
                DukeUtils.echoText(err.getMessage());
            }
        }

    }

    public static String inputText() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
    private static Map<String, CommandExecutor> createMap() {
        return Map.of(
                "bye", CommandExecutor.BYE,
                "list", CommandExecutor.LIST,
                "mark", CommandExecutor.MARK,
                "unmark", CommandExecutor.UNMARK,
                "deadline", CommandExecutor.DEADLINE,
                "todo", CommandExecutor.TODO,
                "event", CommandExecutor.EVENT,
                "delete", CommandExecutor.DELETE
        );
    }
}
