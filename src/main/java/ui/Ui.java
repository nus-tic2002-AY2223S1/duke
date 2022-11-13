package ui;

import java.util.Scanner;
public class Ui {

    public void showLoadingError(){

        System.out.println("Task file not exist!");
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("______________________________________________________");
        System.out.println(logo);
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        System.out.println("______________________________________________________");
    }

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();

    }

    public void showLine(){
        System.out.println("______________________________________________________");
    }

    public void display(String msg){

        System.out.println(msg);

    }

    public void showError(String error){

        System.out.println(error);
    }

}
