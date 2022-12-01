import static config.CommonUtil.*;

public class BotMaster {

    private String botName;
    private String messageDelimiter;

    public BotMaster(String name, String delimiter) {
        this.botName = name;
        this.messageDelimiter = delimiter;
    }

    public void initialization() {
        showLine(this.messageDelimiter);
        System.out.println(singleTab + "Hello! I'm " + this.botName);
        System.out.println(singleTab + "What can I do for you?");
        showLine(this.messageDelimiter);
    }

    public void displayMessage(String inputStr) {
        showLine(this.messageDelimiter);
        System.out.println(singleTab + inputStr);
        showLine(this.messageDelimiter);
    }

    public void termination() {
        showLine(this.messageDelimiter);
        System.out.println(singleTab + byeGreeting);
        showLine(this.messageDelimiter);
    }

}
