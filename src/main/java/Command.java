import java.util.ArrayList;

/**
 * The type Command.
 */
public abstract class Command {

    /**
     * The Command.
     */
    protected final String command;
    /**
     * The Tasks.
     */
    protected ArrayList<Task> tasks;

    /**
     * Instantiates a new Command.
     *
     * @param tasks   the tasks
     * @param command the command
     */
    public Command(ArrayList<Task> tasks, String command) {
        this.tasks = tasks;
        this.command = command.toLowerCase();
    }

    /**
     * Print message.
     *
     * @param task the task
     */
    public void printMessage(Task task) {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\tGot it. I've added this task:\n\t" + task);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        System.out.println("-------------------------------------------------------------------");
    }

    /**
     * Execute array list.
     *
     * @return the array list
     * @throws IllegalCommandException the illegal command exception
     */
    public abstract ArrayList<Task> execute() throws IllegalCommandException;
}
