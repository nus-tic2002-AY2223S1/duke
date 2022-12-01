import java.util.ArrayList;
import java.util.List;

import static config.CommonUtil.*;

public class BotMaster {

    private String botName;
    private String messageDelimiter;

    private List<String> botPocket;

    public BotMaster(String name, String delimiter) {
        this.botName = name;
        this.messageDelimiter = delimiter;
        this.botPocket = new ArrayList<>();
    }

    public void initialization() {
        showLine(this.messageDelimiter);
        System.out.println(singleTab + "Hello! I'm " + this.botName);
        System.out.println(singleTab + "What can I do for you?");
        showLine(this.messageDelimiter);
    }

    public void displayMessage(String inputStr) {
        showLine(this.messageDelimiter);
        System.out.println(singleTab + addedConstant +  inputStr);
        botPocket.add(inputStr);
        showLine(this.messageDelimiter);
    }

    public void displayPocket(){
        showLine(this.messageDelimiter);
        int counter = 1;
        for (String item: botPocket) {
            System.out.println(singleTab + counter + ". " + item);
            counter++;
        }
        showLine(this.messageDelimiter);
    }

    public void termination() {
        showLine(this.messageDelimiter);
        System.out.println(singleTab + byeGreeting);
        showLine(this.messageDelimiter);
    }

}
