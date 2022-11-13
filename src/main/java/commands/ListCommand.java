package commands;

public class ListCommand extends Command{

    public void execute() {
        instance.printTasks();
    }
}
