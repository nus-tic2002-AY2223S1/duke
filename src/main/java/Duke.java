
import java.util.*;

import entity.CommandParser;
import constant.CommandExecutor;
import entity.Storage;
import entity.TaskList;
import entity.Ui;
import exceptions.DukeException;

public class Duke {
    private static final Map<String, CommandExecutor> COMMAND_EXECUTOR_MAP = CommandParser.createMap();
    public static void main(String[] args) throws DukeException {
        // DUKE greeting message
        Ui.dukeInit();

        String inputs;
        CommandExecutor command;
        TaskList taskList = new TaskList();

        Storage file = new Storage();
        file.read(taskList);

        while (true) {
            inputs = CommandParser.getInputs();
            command = CommandParser.getCommandExecutor(inputs);
            command = command != null ? command : CommandExecutor.UNDEFINED;
            if (command == CommandExecutor.BYE) {
                command.execute(taskList, inputs);
                break;
            }
            try {
                command.execute(taskList, inputs);
            } catch (DukeException err) {
                Ui.showErrMessage(err);
            }
        }
    }
}
