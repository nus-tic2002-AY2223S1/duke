package UI;

import Tasks.TaskInterface;

import java.util.ArrayList;

public class DukeUI implements UIInterface {
    private String name;
    public DukeUI(String name) {
        this.name = name;
        welcome();
    }
    private void welcome() {
        seperator();
        printlntab("Hello! I'm "  + name);
        printlntab("What can I do for you?");
        seperator();
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
