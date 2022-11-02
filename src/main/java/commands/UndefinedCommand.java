package commands;

import exceptions.DukeException;

public class UndefinedCommand extends Command{

    public void execute() {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
