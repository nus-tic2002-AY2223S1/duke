package entry;

import constant.Constant;
import form.Form;
import exception.DukeException;
import service.ParserManager;
import ui.ConsoleUI;
import ui.UI;
import service.command.Command;
import service.CommandManager;

public class Duke {

    /**
     * @description entry of program, start chatbot
     * @author Dex
     * @date 2022/08/31
     */
    public void run() {
        UI ui = new ConsoleUI();
        ui.displayGreeting();
        while (Constant.IS_RUNNING) {
            try {
                Form form = ParserManager.parseForm(ui.getInput());
                Command command = CommandManager.getCommand(form.getCommand());
                command.execute(form);
                ui.displayLineBreak();
            } catch (DukeException dukeException) {
                ui.displayDukeErrorMsg(dukeException.getMessage());
            } catch (Exception exception) {
                ui.displayUnknownErrorMsg(exception);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
