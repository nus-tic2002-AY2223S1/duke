package Duke;
import Interface.Cmd;
import Interface.Parser;
import Interface.Ui;
import Util.DukeException;
import java.io.FileNotFoundException;

public class Duke {
    private final Ui ui;
    private TaskList t;
    private final Parser p;

    public Duke(String path){
        ui = new Ui();
        p = new Parser();
        Storage s = new Storage(path);
        try{
            t = new TaskList(s.load());
        }catch (FileNotFoundException e){
            t = new TaskList();
        }
    }
    public static void main(String[] args) {
        new Duke("save/output.txt").run();
    }

    public void run() {
        ui.sendWelcomeMessage(t);
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
