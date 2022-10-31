package Duke;
import Interface.Cmd;
import Interface.Parser;
import Interface.Ui;
import Util.DukeException;

public class Duke {
    private final Ui ui;
    private final TaskList t;

    public Duke(){
        ui = new Ui();
        t = new TaskList();
    }
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.sendWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                Cmd c = Parser.parse(ui.readIn());
                c.run(t.getList());
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.sendGenericFatal(e.getMessage());
            }
        }
    }
    }
