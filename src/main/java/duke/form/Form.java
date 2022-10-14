package duke.form;

public class Form {

    private String metaData;

    private String command;

    public Form() {
    }

    public Form(String metaData) {
        this.metaData = metaData;
    }

    public Form(String metaData, String command) {
        this.metaData = metaData;
        this.command = command;
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
