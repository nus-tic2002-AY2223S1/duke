package entity;

public class Form {

    private String command;

    private String metaData;

    public Form(String metaData) {
        this.metaData = metaData;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
