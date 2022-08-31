package entity;

public class Form {

    private String metaData;

    private String command;

    private int operateIndex;


    public Form(String metaData) {
        this.metaData = metaData;
    }

    public Form(String metaData, String command) {
        this.metaData = metaData;
        this.command = command;
    }

    public Form(String metaData, String command, int operateIndex) {
        this.metaData = metaData;
        this.command = command;
        this.operateIndex = operateIndex;
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

    public int getOperateIndex() {
        return operateIndex;
    }

    public void setOperateIndex(int operateIndex) {
        this.operateIndex = operateIndex;
    }
}
