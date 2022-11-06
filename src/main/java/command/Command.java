package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class Command {

    public boolean isExit = false;

    /**
     * Command constructor
     */
    Command() {
    }

    /**
     * This constructor set a boolean value for exitValue based on user input
     *
     * @param exitValue boolean value to exit the chatbot process
     */
    Command(boolean exitValue) {
        this.isExit = exitValue;
    }


    /**
     * This method set the format for execute method for other classes that inherit Command class to override
     *
     * @param tasks   task list class
     * @param ui      ui class to show messages
     * @param storage storage class that has the load or save method
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    /**
     * Return boolean value that will decide whether to end the session
     *
     * @return boolean value
     */
    public boolean getExitValue() {
        return this.isExit;
    }
}
