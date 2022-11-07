import common.Keyword;
import logic.BotUseCase;
import logic.TaskManager;
import ui.UIDelegate;

import java.util.Scanner;

/**
 * Redirect the command to taskManager where has the logic
 */
public class Bot implements UIDelegate {
    private TaskManager taskManager;

    public Bot() {

    }

    public void setup(BotUseCase router) {
        taskManager = new TaskManager(router);
    }

    public void start() {
        taskManager.start();
    }

    public void add(String text) {
        taskManager.addNewTask(text);
    }

    public void showList() {
        taskManager.showList();
    }

    public void mark(String i,  boolean value){
        int index = Integer.parseInt(i);
        taskManager.markTask(index - 1, value);
    }

    public void delete(String number) {
        int index = Integer.parseInt(number);
        taskManager.remove(index - 1);
    }

    public void goodbye() {
        taskManager.goodbye();
    }

    public void showFiles() { taskManager.showFiles(); }

    public void setActiveFile(String alias) {
        taskManager.setActiveFile(alias);
    };
    public void createNewFile(String alias) {
        taskManager.createNewFile(alias);
    };
    public void getActiveFile() {
        taskManager.getActiveFile();
    }

    public void invalidFormat(String valid, String invalid){
        taskManager.showInvalidFormat(valid, invalid);
    }

    public void find(String keyword) {
        taskManager.find(keyword);
    }

    @Override
    public boolean userInput(String line) {
        if (Keyword.getKeyword(line) != Keyword.Bye) {
            Keyword key = Keyword.getKeyword(line);
            if(Keyword.validateFormat(line) == false){
                this.invalidFormat(Keyword.validFormat(key), line);
                return true;
            }
            if (key == Keyword.List) {
                this.showList();
            } else if (key == Keyword.Mark) {
                String part = line.split(" ")[1];
                this.mark(part, true);
            } else if (key == Keyword.Unmark) {
                String part = line.split(" ")[1];
                this.mark(part, false);
            } else if (key == Keyword.Delete) {
                String part = line.split(" ")[1];
                this.delete(part);
            } else if (key == Keyword.Files) {
                this.showFiles();
            } else if (key == Keyword.Load) {
                String part = line.split(" ")[1];
                this.setActiveFile(part);
            } else if (key == Keyword.Create) {
                String part = line.split(" ")[1];
                this.createNewFile(part);
            } else if (key == Keyword.Active) {
                this.getActiveFile();
            } else if (key == Keyword.Find) {
                String part = line.split(" ")[1];
                this.find(part);
            } else {
                this.add(line);
            }
            return true;
        }
        this.goodbye();
        return false;
    }
}
