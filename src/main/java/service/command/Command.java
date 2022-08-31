package service.command;

import entity.Form;
import service.TaskManager;

public abstract class Command {

    protected static TaskManager taskManager = TaskManager.getInstance();

    public abstract void execute(Form form);
}
