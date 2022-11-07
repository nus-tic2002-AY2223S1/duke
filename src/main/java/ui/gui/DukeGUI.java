package ui.gui;

import data.FileInfo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import task.TaskInterface;
import ui.UIDelegate;
import ui.UIInterface;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DukeGUI implements UIInterface, ScreenDelegate {
    private String name;
    private UIDelegate delegate;
    private Screen screen;

    public DukeGUI(String name, UIDelegate delegate)  {
        this.name = name;
        this.delegate = delegate;
    }

    private void welcome() {
        String text = "Hello! I'm "  + name + '\n';
        text += "What can I do for you?";
        screen.dukeReply(text);
    }

    @Override
    public void start() {
        try {
            screen = new Screen(name, this);
            screen.init();
            Platform.startup(() -> {
                // create primary stage
                Stage stage = new Stage();
                try {
                    screen.start(stage);
                    welcome();
                } catch (Exception e) {

                }
            });
        } catch(Exception e) {

        }
    }

    @Override
    public void goodbye() {
        screen.dukeReply("Bye. Hope to see you again soon!");
        screen.exit();
    }
    @Override
    public void addSuccess(TaskInterface task) {
        String text = "Got it. I've added this task:\n";
        text += task.getString();
        screen.dukeReply(text);
    }
    @Override
    public void unsupportedFormat(String text) {
        screen.dukeReply("☹ OOPS!!! The description of a "+text+" cannot be empty.");
    }
    @Override
    public void unsupportedTaskType() {
        screen.dukeReply("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    @Override
    public void indexOutOFBound() {
        screen.dukeReply("☹ OOPS!!! I'm sorry, entered number is not in the list");
    }
    @Override
    public void invalidFormat(String text) { screen.dukeReply("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");}
    @Override
    public void showList(ArrayList<TaskInterface> lists) {
        String text = "Here are the tasks in your list:";
        int i = 1;
        for(TaskInterface each : lists) {
            text += "\n" + i + ". " + each.getString();
            i++;
        }
        screen.dukeReply(text);
    }
    @Override
    public void markSuccess(TaskInterface task) {
        boolean value = task.isDone();
        if(value == true) {
            screen.dukeReply("Nice! I've marked this task as done:\n" + task.getString());
        } else {
            screen.dukeReply("OK, I've marked this task as not done yet\n:"+ task.getString());
        }
    }
    @Override
    public void markFailed(TaskInterface task) {
        boolean value = task.isDone();
        String text = "";
        if(value == true) {
            text += "This task is already marked as done\n";
        } else {
            text += "This task is already marked as not done\n";
        }
        text += task.getString();
        screen.dukeReply(text);
    }
    @Override
    public void deleteSuccess(TaskInterface task) {
        screen.dukeReply("Noted. I've removed this task:\n" + task.getString());

    }
    @Override
    public void displaySize(int size) {
        screen.dukeReply("Now you have "+size+" tasks in the list.");
    }

    @Override
    public void customError(String text) {
        screen.dukeReply(text);
    }
    @Override
    public void unexpectedError() {
        screen.dukeReply("Unfortunately unexpected error has occur, please try again or restart the system!");
    }

    @Override
    public void addFileSuccess(String text) {
        String line = "Successfully added new save file with alias " + text + "\n";
        line += text +" is the current active file";
        screen.dukeReply(line);
    }

    @Override
    public void addFileFailed(String text) {
        screen.dukeReply("Please choose other alias, " + text + " is already exist");
    }

    @Override
    public void showFiles(ArrayList<FileInfo> files) {
        String line = "Here are the saved files:";
        int i = 1;
        for(FileInfo each: files) {
            line += "\n" + i + ". " + each.getAlias() + (each.isActive() ? " [Active]" : "");
            i++;
        }
        screen.dukeReply(line);
    }

    @Override
    public void setActiveSuccess(String alias){
        screen.dukeReply("Successfully activate " + alias + " file");
    }

    @Override
    public void getActiveFile(String alias) {
        screen.dukeReply("Your current active file is "+ alias);
    }

    @Override
    public void invalidCommandFormat(String valid, String invalid) {
        String text = "";
        text += "You have entered wrong format for the command\n";
        text += "Your command: " + invalid + "\n";
        text += "Reason: " + valid + "\n";
        screen.dukeReply(text);
    }

    @Override
    public void showFilteredList(ArrayList<TaskInterface> lists) {
        String text = "Here are the matching tasks in your list:";
        int i = 1;
        for(TaskInterface each : lists) {
            text += "\n" + i + ". " + each.getString();
            i++;
        }
        screen.dukeReply(text);
    }

    @Override
    public void userInput(String text) {
        delegate.userInput(text);
    }
}
