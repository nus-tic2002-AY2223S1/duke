package Duke;
import Interface.Cmd;
import Interface.Parser;
import Interface.Ui;
import Util.DukeException;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static Task[] arr = {};
    public static ArrayList<Task> arrayList = new ArrayList<>(Arrays.asList(arr));
    private final Ui ui;

    public Duke(){
        ui = new Ui();
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
                c.run(arrayList);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.sendGenericFatal(e.getMessage());
            }
        }
    }
    }
