import model.Task;

import java.util.ArrayList;
import java.util.List;

import static config.CommonUtil.*;

public class BotMaster {

    private String botName;
    private String messageDelimiter;

    private List<Task> botPocket;

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
        botPocket.add(new Task(inputStr));
        showLine(this.messageDelimiter);
    }

    public void markTask(String position) {
        showLine(this.messageDelimiter);
        System.out.println(singleTab + markedMessage);
        botPocket.get(Integer.valueOf(position)-1).markTask(true);
        Task selectedTask = botPocket.get(Integer.valueOf(position)-1);
        System.out.println(singleTab + "  " + selectedTask.toString());
        showLine(this.messageDelimiter);
    }

    public void unmarkTask(String position) {
        showLine(this.messageDelimiter);
        System.out.println(singleTab + unmarkedMessage);
        botPocket.get(Integer.valueOf(position)-1).markTask(false);
        Task selectedTask = botPocket.get(Integer.valueOf(position)-1);
        System.out.println(singleTab + "  " + selectedTask.toString());
        showLine(this.messageDelimiter);
    }

    public void displayPocket(){
        showLine(this.messageDelimiter);
        int counter = 1;
        for (Task item: botPocket) {
            System.out.println(singleTab + counter + "." + item.toString());
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
