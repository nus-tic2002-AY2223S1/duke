import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The type Add deadline command.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Instantiates a new Add deadline command.
     *
     * @param tasks   the tasks
     * @param command the command
     */
    public AddDeadlineCommand(ArrayList<Task> tasks, String command) {
        super(tasks, command);
    }

    @Override
    public ArrayList<Task> execute() throws IllegalCommandException, IllegalArgumentException, DateTimeParseException {
        String[] commands = this.command.split(" ");

        if (!(command.contains("deadline") && command.contains("/by") && commands[0].equalsIgnoreCase("deadline") && commands.length >= 2)) {
            throw new IllegalCommandException("Invalid Command Entered!");
        }

        int index = -1;
        for (int i = 0; i < commands.length; i++) {
            String str = commands[i];
            if (str.equalsIgnoreCase("/by")) {
                index = i;
            }
        }

        LocalDate date = DateFormatter.getDateFromString(commands[index + 1]);
        if (index == -1 || index == commands.length - 1) {
            throw new IllegalCommandException("Invalid Command Entered!");
        }

        Deadline deadline = new Deadline(this.getDescriptionFromCommand(this.command), date);
        this.tasks.add(deadline);
        printMessage(deadline);
        return this.tasks;
    }

    private String getDescriptionFromCommand(String command) {
        String temp = command.replace("deadline ", "");
        int index = temp.indexOf("/by");
        return temp.substring(0, index);
    }
}
