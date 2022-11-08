package duke.entry;

import duke.constant.Constant;
import duke.dto.ResponseDto;
import duke.exception.DukeException;
import duke.form.Form;
import duke.service.CommandManager;
import duke.service.ParserManager;
import duke.service.TaskManager;
import duke.service.command.Command;
import duke.ui.ConsoleUI;
import duke.ui.UI;

/**
 * Entry class to start application.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class Duke {

    /**
     * Location of persisting the task data.
     */
    private String dataFilePath;

    /**
     * Multi args constructors.
     *
     * @param dataFilePath: Location of file to store data.
     */
    public Duke(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    /**
     * Returns data file location.
     *
     * @return Data file location.
     */
    public String getDataFilePath() {
        return dataFilePath;
    }

    /**
     * Inits all components in the program.
     */
    private void init() {
        // init Task Manager
        TaskManager.init(getDataFilePath());
    }

    /**
     * Runs the program
     */
    public void run() {
        // init app components
        init();

        // display greeting
        UI ui = new ConsoleUI();
        ui.renderGreetingMessage();

        // start program
        while (Constant.IS_RUNNING) {
            try {
                Form form = ParserManager.parseForm(ui.getDukeCommandInput());
                Command command = CommandManager.getCommand(form.getCommand());
                ResponseDto<?> responseDto = command.execute(form);
                ui.renderResponse(responseDto);
            } catch (DukeException dukeException) {
                ui.renderDukeErrorMsg(dukeException.getMessage());
            } catch (Exception exception) {
                ui.renderUnknownErrorMsg(exception);
            }
        }
    }

    /**
     * Runs the application, entry method of the application.
     *
     * @param args: Arguments from the user.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/data.json");
        duke.run();
    }
}
