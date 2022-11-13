package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.SaveException;

import java.io.IOException;
import java.util.HashMap;


/**
 * Main class of the program with the entry function
 */
public class Duke {
    public static final String DEFAULT_SAVE_PATH = "duke.save";
    public static final String SAVE_PATH = "duke.save";
    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;
    protected HashMap<String, String> arguments;
    public Duke(String filepath) {
        ui = new Ui();
        Storage storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.printLoadSaveException(filepath, e);
            tasks = new TaskList(storage);
        }
        parser = new Parser(ui, tasks);
    }
        public void run() {
            ui.printWelcome();

            while (true) {
                String fullCommand = ui.read();
                if (fullCommand == null) {
                    // Reach EOF, exit the program
                    break;
                }
            ui.printLine();
                try {
                Command cmd = parser.parse(fullCommand);
                cmd.execute();
                    if (cmd.isExit()) {
                        break;
                    }
            } catch (Exception e) {
                ui.printException(e);
            } finally {
                ui.printLine();
        }
            ui.printLine();
        }
        ui.close();
    }

    /**
     * Program entry point
     * @param args Command line arguments provided
     */
        public static void main(String[] args) {
            String filepath = DEFAULT_SAVE_PATH;
            if (args.length > 0) {
                // If additional argument is provided, take 1st argument as the save filepath
                filepath = args[0];
            }
            new Duke(filepath).run();
        }

    public String getR(String fullCommand) throws InvalidInputException, SaveException, IOException, DukeException {
        try {
            Command cmd = parser.parse(fullCommand);
            return cmd.executeGui();

        } catch (InvalidInputException | DukeException e) {
            throw new RuntimeException(e);
        }
    }

}
