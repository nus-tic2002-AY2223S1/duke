package commands;

import entity.Storage;

public abstract class Command {
    public static final Storage instance = Storage.getInstance();
    public abstract void execute();
}
