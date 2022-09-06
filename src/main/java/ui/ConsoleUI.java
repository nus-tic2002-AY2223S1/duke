package ui;

import util.ExceptionUtil;

import java.util.Scanner;

public class ConsoleUI implements UI {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        displayLineBreak();
        System.out.println("Greeting from Duke! ;>");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("|A quick guidance for you                                                 |");
        System.out.println("|Enter `show_command` to check current supported command                  |");
        System.out.println("|Any input which is not in supported command will be treated as `add task`|");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Feed me the first command for today!");
        displayLineBreak();
    }

    @Override
    public void displayLineBreak() {
        System.out.println("+-----------------------------------------+");
    }

    @Override
    public void displayDukeErrorMsg(String errorMsg) {
        System.out.println(":(");
        System.out.println("cannot execute command due to error raised");
        System.out.println(errorMsg);
        displayLineBreak();
    }

    @Override
    public void displayUnknownErrorMsg(Throwable throwable) {
        System.out.println(":(");
        System.out.println("unknown error raised, please contact developer for assistance");
        // actual error message should keep internally, prevent exposure to user. (for dev purpose, do not comment out the given code)
        System.out.println(ExceptionUtil.getStackTraceAsString(throwable));
        displayLineBreak();
    }

    public String getInput() {
        System.out.print("~@duke >>> ");
        return scanner.nextLine();
    }
}
