import common.Keyword;
import data.*;
import logic.BotUseCase;
import ui.DukeUI;
import ui.UIDelegate;
import ui.UIInterface;

import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Bot bot = new Bot();
        UIInterface ui = new DukeUI("lamBDA", bot);
        FileInterface fileData = new FileFactory("dukefile.txt");
        DataInterface data = new DataFileFactory("dukefile.txt", "duke-data");
        BotUseCase router = new Router(ui, data, fileData);
        bot.setup(router);
        bot.start();

    }
}