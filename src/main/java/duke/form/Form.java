package duke.form;

/**
 * Base form stores the parsed parameters from console.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class Form {

    /**
     * Raw data of input.
     */
    private String metaData;

    /**
     * Command name.
     */
    private String command;

    /**
     * Default no args constructor.
     */
    public Form() {}

    /**
     * Multi args constructor.
     *
     * @param metaData: Raw input from user.
     */
    public Form(String metaData) {
        this.metaData = metaData;
    }

    /**
     * Multi args constructor.
     *
     * @param metaData: Raw input from user.
     * @param command: Command name.
     */
    public Form(String metaData, String command) {
        this.metaData = metaData;
        this.command = command;
    }

    /**
     * Returns command name.
     *
     * @return Command name.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets command name.
     *
     * @param command: Target command name.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Returns meta data.
     *
     * @return Raw command input from user.
     */
    public String getMetaData() {
        return metaData;
    }

    /**
     * Sets meta data.
     *
     * @param metaData: Raw command input from user.
     */
    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
