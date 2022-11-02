package commands;

import entity.Ui;

public class ByeCommand extends Command{
    public void execute() {
        Ui.echoText("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
