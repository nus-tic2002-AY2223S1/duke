package commands;

import entity.Ui;

public class SaveCommand extends Command{

    public void execute() {
        instance.write(instance.writeTasksToFile());
        Ui.echoText("The tasks have been saved");
    }
}
