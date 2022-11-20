import java.util.ArrayList;

/**
 * The type Add to do command.
 */
public class AddToDoCommand extends Command {

    /**
     * Instantiates a new Add to do command.
     *
     * @param tasks   the tasks
     * @param command the command
     */
    public AddToDoCommand(ArrayList<Task> tasks, String command) {
        super(tasks, command);
    }

    @Override
    public ArrayList<Task> execute() throws IllegalCommandException {

        String[] commands = this.command.split(" ");

        if (!(command.contains("todo") && commands[0].equalsIgnoreCase("todo") && commands.length >= 2)) {
            throw new IllegalCommandException("Command to add To-DO is invalid!");
        }

        Todo todo = new Todo(this.getDescriptionFromCommand(this.command));
        this.tasks.add(todo);
        printMessage(todo);
        return tasks;
    }

    private String getDescriptionFromCommand(String command) {
        return command.replace("todo ", "");
    }

}
