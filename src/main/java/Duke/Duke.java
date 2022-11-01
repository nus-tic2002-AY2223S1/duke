package Duke;
import Interface.Cmd;
import Interface.Parser;
import Interface.Ui;
import Util.DukeException;

public class Duke {
    private final Ui ui;
    private final TaskList t;
    private final Parser p;

    public Duke(){
        ui = new Ui();
        t = new TaskList();
        p = new Parser();
    }
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.sendWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                Cmd c = p.readIn().parse();
                c.run(t.getList());
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.sendGenericFatal(e.getMessage());
            }
        }
    }
    }
