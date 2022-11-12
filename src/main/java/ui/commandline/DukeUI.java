package ui.commandline;

import data.FileInfo;
import task.TaskInterface;
import ui.UIDelegate;
import ui.UIInterface;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The command line UI for the duke by implementing UIInterface
 */
public class DukeUI implements UIInterface {
    private String name;
    private UIDelegate delegate;
    public DukeUI(String name, UIDelegate delegate) {
        this.name = name;
        this.delegate = delegate;
        welcome();
    }
    private void welcome() {
        seperator();
        printlntab("Hello! I'm "  + name);
        printlntab("What can I do for you?");
        seperator();
    }

    @Override
    public void start() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (delegate.userInput(line)) {
            line = in.nextLine();
        }
    }
    @Override
    public void goodbye() {
        seperator();
        printlntab("Bye. Hope to see you again soon!");
        seperator();
    }
    @Override
    public void addSuccess(TaskInterface task) {
        seperator();
        int size = 0;
        System.out.println(appendTab("Got it. I've added this task:"));
        printlntab(task.getString());
        seperator();
    }
    @Override
    public void unsupportedFormat(String text) {
        printlntab("☹ OOPS!!! The description of a "+text+" cannot be empty.");
    }
    @Override
    public void unsupportedTaskType() {
        printlntab("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    @Override
    public void indexOutOFBound() {
        printlntab("☹ OOPS!!! I'm sorry, entered number is not in the list");
    }
    @Override
    public void invalidFormat(String text) { printlntab("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");}
    @Override
    public void showList(ArrayList<TaskInterface> lists) {
        seperator();
        printlntab("Here are the tasks in your list:");
        int i = 1;
        for(TaskInterface each : lists) {
            printlntab(i + ". " + each.getString());
            i++;
        }
        seperator();
    }
    @Override
    public void markSuccess(TaskInterface task) {
        seperator();
        boolean value = task.isDone();
        if(value == true) {
            System.out.println(appendTab("Nice! I've marked this task as done:"));
        } else {
            System.out.println(appendTab("OK, I've marked this task as not done yet:"));
        }
        printlntab(task.getString());
        seperator();
    }
    @Override
    public void markFailed(TaskInterface task) {
        boolean value = task.isDone();
        seperator();
        if(value == true) {
            System.out.println(appendTab("This task is already marked as done"));
        } else {
            System.out.println(appendTab("This task is already marked as not done"));
        }
        printlntab(task.getString());
        seperator();
    }
    @Override
    public void deleteSuccess(TaskInterface task) {
        printlntab("Noted. I've removed this task:");
        printlntab(task.getString());

    }
    @Override
    public void displaySize(int size) {
        printlntab("Now you have "+size+" tasks in the list.");
    }

    @Override
    public void customError(String text) {
        printlntab(text);
    }
    @Override
    public void unexpectedError() {
        printlntab("Unfortunately unexpected error has occur, please try again or restart the system!");
    }

    @Override
    public void addFileSuccess(String text) {
        seperator();
        printlntab("Successfully added new save file with alias " + text);
        printlntab(text +" is the current active file");
        seperator();
    }

    @Override
    public void addFileFailed(String text) {
        printlntab("Please choose other alias, " + text + " is already exist");
    }

    @Override
    public void showFiles(ArrayList<FileInfo> files) {
        seperator();
        printlntab("Here are the saved files:");
        int i = 1;
        for(FileInfo each: files) {
            printlntab(i + ". " + each.getAlias() + (each.isActive() ? " [Active]" : ""));
            i++;
        }
        seperator();
    }

    @Override
    public void setActiveSuccess(String alias){
        printlntab("Successfully load " + alias + " file");
    }
    @Override
    public void setActiveFailed(String alias) {
        printlntab("Failed to load "+ alias +" file, please check if it is exist in the system");
    }

    @Override
    public void getActiveFile(String alias) {
        printlntab("Your current active file is "+ alias);
    }

    @Override
    public void invalidCommandFormat(String valid, String invalid) {
        seperator();
        printlntab("You have entered wrong format for the command");
        printlntab("Your command: " + invalid);
        printlntab("Reason: " + valid);
        seperator();
    }

    @Override
    public void showFilteredList(ArrayList<TaskInterface> lists) {
        seperator();
        printlntab("Here are the matching tasks in your list:");
        int i = 1;
        for(TaskInterface each : lists) {
            printlntab(i + ". " + each.getString());
            i++;
        }
        seperator();
    }

    private void seperator() {
        System.out.println("\t---------------------------------------------");
    }
    private void printlntab(String text) {
        System.out.println(appendTab(text));
    }
    private String appendTab(String text) {
        return "\t" + text;
    }
}
