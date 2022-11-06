package entity;

public class Command {
    private String command = "";
    private int index = -1;
    private String description = "";
    private String datetime = "";
    
    /**
     * Command Constructor
     */
    public Command() {
    }
    
    /**
     * Return task command type
     *
     * @return command variable
     */
    public String getCommand() {
        return command;
    }
    
    /**
     * Set task command type
     *
     * @param command the command type that user inputted
     */
    public void setCommand(String command) {
        this.command = command;
    }
    
    /**
     * Return task index
     *
     * @return index variable
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Set task index
     *
     * @param index the task index that user inputted
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * Return task datetime
     *
     * @return datetime variable
     */
    public String getDatetime() {
        return datetime;
    }
    
    /**
     * Set task datetime
     *
     * @param datetime the datetime that user inputted
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    /**
     * Return task description
     *
     * @return description variable
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Set task description
     *
     * @param description the task description that user inputted
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
