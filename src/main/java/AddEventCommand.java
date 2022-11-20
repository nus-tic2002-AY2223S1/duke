import java.util.ArrayList;

/**
 * The type Add event command.
 */
public class AddEventCommand extends Command {

    private static final String keyword = "/at";
    private static final String commandType = "event";

    /**
     * Instantiates a new Add event command.
     *
     * @param tasks   the tasks
     * @param command the command
     */
    public AddEventCommand(ArrayList<Task> tasks, String command) {
        super(tasks, command);
    }

    @Override
    public ArrayList<Task> execute() throws IllegalCommandException {
        String[] commands = this.command.split(" ");

        if (!(command.contains(commandType) && command.contains(keyword) && commands[0].equalsIgnoreCase(commandType) && commands.length >= 4)) {
            throw new IllegalCommandException("Invalid Command Entered!");
        }

        int index = -1;
        for (int i = 0; i < commands.length; i++) {
            String str = commands[i];
            if (str.equalsIgnoreCase(keyword)) {
                index = i;
            }
        }

        if (index == -1 || index == commands.length - 1) {
            throw new IllegalCommandException("Invalid Command Entered!");
        }

        Event event = new Event(getDescriptionFromCommand(this.command), getEventTime(commands, index + 1));
        this.tasks.add(event);
        printMessage(event);

        return this.tasks;
    }

    private String getDescriptionFromCommand(String command) {
        String temp = command.replace("event ", "");
        System.out.println("Temp is: " + temp);
        int index = temp.indexOf("/at");
        return temp.substring(0, index);
    }

    private String getEventTime(String[] commands, int index) {
        String str = "";
        for (int i = index; i < commands.length; i++) {
            str += commands[i] + " ";
        }
        return str.trim();
    }
}
