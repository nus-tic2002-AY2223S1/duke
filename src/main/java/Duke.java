import common.Keyword;
import data.*;
import logic.BotUseCase;
import ui.DukeUI;
import ui.UIInterface;

import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        UIInterface ui = new DukeUI("lamBDA");
        FileInterface fileData = new FileFactory("dukefile.txt");
        DataInterface data = new DataFileFactory("dukefile.txt", "duke-data");
        BotUseCase router = new Router(ui, data, fileData);
        Bot bot = new Bot(router);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (Keyword.getKeyword(line) != Keyword.Bye) {
            Keyword key = Keyword.getKeyword(line);
            if(Keyword.validateFormat(line) == false ){
                bot.invalidFormat(Keyword.validFormat(key), line);
                line = in.nextLine();
                continue;
            }
            if (key == Keyword.List) {
                bot.showList();
            } else if (key == Keyword.Mark) {
                String part = line.split(" ")[1];
                bot.mark(part, true);
            } else if (key == Keyword.Unmark) {
                String part = line.split(" ")[1];
                bot.mark(part, false);
            } else if (key == Keyword.Delete) {
                String part = line.split(" ")[1];
                bot.delete(part);
            } else if (key == Keyword.Files) {
                bot.showFiles();
            } else if (key == Keyword.Load) {
                String part = line.split(" ")[1];
                bot.setActiveFile(part);
            } else if (key == Keyword.Create) {
                String part = line.split(" ")[1];
                bot.createNewFile(part);
            } else if (key == Keyword.Active) {
                bot.getActiveFile();
            } else if (key == Keyword.Find) {
                String part = line.split(" ")[1];
                bot.find(part);
            } else {
                bot.add(line);
            }
            line = in.nextLine();
        }
        bot.goodbye();
    }
}