package command;

public class ByeCommand extends Command {

    /**
     * This method will set exitValue a specific boolean value.
     * This would end the session depending on the value given.
     *
     * @param exitValue boolean value
     */
    public ByeCommand(boolean exitValue) {
        super(exitValue);
    }
}
