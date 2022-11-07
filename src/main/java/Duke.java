import data.*;
import logic.BotUseCase;
import ui.gui.DukeGUI;
import ui.UIInterface;


public class Duke {
    public static void main(String[] args) {
        Bot bot = new Bot();
        UIInterface ui = new DukeGUI("Duke", bot);
        FileInterface fileData = new FileFactory("dukefile.txt");
        DataInterface data = new DataFileFactory("dukefile.txt", "duke-data");
        BotUseCase router = new Router(ui, data, fileData);
        bot.setup(router);
        bot.start();

    }
}