package Ui;

import Entity.Task;

import java.util.Scanner;

public class Ui {
    public static String line = "\t____________________________________________________________";

    public void showWelcome() {
        String greeting = line + "\n\tHello! I'm Duke,your personal planner.\n" +
                "\tWhat can I do for you?\n"+
                "\t(Hint!: You can key \"help\" to explore more. Enjoy~~ :))\n"
                +line;
        System.out.println(greeting);
    }

    public void printFind(){
        System.out.println("\tHere are the matching tasks in your list:");
    }
    public void showLine() {
        System.out.println(line);
    }

    public void marktaskAsDone(){
        System.out.println("\tNice! I've marked this task as done:");
    }

    public void unmarkTask(){
        System.out.println("\tOK, I've marked this task as not done yet:");
    }

    public void printTaskStatus(Task task){
        String icon = String.format("\t[%s] ",task.getStatusIcon());
        System.out.println(icon + task.getDescription());
    }

    public void showError(String input){
        System.out.println("\t"+input);
    }

//todo: need update here
    public void showLoadingError(){
        System.out.println("\t");

    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return inputCleaner(in.nextLine());
    }

    private String inputCleaner(String input) {
        return input.trim();
    }
}
