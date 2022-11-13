
import entity.CommandParser;
import constant.CommandExecutor;
import entity.Storage;
import entity.Ui;
import exceptions.DukeException;

public class Duke {
    public static String inputs;
    public static CommandExecutor command;

    /**
     * Duke program initiation
     */
    public static void init() {
        Ui.dukeInit();
        Storage instance = Storage.getInstance();
        instance.read();
    }

    /**
     * main program
     *
     * @param args na
     * @throws DukeException duke exception
     */
    public static void main(String[] args) throws DukeException {
        // DUKE greeting message
        init();

        while (true) {
            inputs = CommandParser.getInputs();
            command = CommandParser.getCommandExecutor(inputs);
            try {
                command.execute(inputs);
            } catch (DukeException err) {
                Ui.showErrMessage(err);
            }
        }
    }
}
