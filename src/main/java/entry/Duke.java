package entry;

import constant.CommonConstant;
import entity.Form;
import service.Parser;
import ui.ConsoleUI;
import ui.UI;
import service.command.Command;
import service.CommandManager;

public class Duke {

    public void run() {
        UI ui = new ConsoleUI();
        ui.displayGreeting();
        while (CommonConstant.IS_RUNNING) {
            Form form = Parser.parseForm(ui.getInput());
            Command command = CommandManager.getCommand(form.getCommand());
            command.execute(form);
            ui.displayLineBreak();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
