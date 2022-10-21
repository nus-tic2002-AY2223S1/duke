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

public class Duke {

    private String dataFilePath;

    public Duke() {}

    public Duke(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public String getDataFilePath() {
        return dataFilePath;
    }

    /**
     * @description init all components
     * @author Dex
     * @date 2022/09/16
     */
    private void init() {
        // init Task Manager
        TaskManager.init(getDataFilePath());
    }

    /**
     * @description entry of program, start chatbot
     * @author Dex
     * @date 2022/08/31
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

    public static void main(String[] args) {
        Duke duke = new Duke("data/data.json");
        duke.run();
    }
}
