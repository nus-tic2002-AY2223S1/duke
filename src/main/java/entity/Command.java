package entity;

public class Command {
    private String command;
    private int index = -1;
    private String description;
    private String datetime;
    
    public Command() {
    }
    
    public String getCommand() {
        return command;
    }
    
    public void setCommand(String command) {
        this.command = command;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public String getDatetime() {
        return datetime;
    }
    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
