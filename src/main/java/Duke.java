import Helpers.CommonHelper;
import Helpers.MessageConstants;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static Task task = new Task();
    public static void main(String[] args) {
        intro();
    }

    private static void intro(){
        String logo =
                  " _____             _   \n"
                + "/  __ \\_   _ _  __|_| ___\n"
                + "| |   | | | | |/ /| |/ _ \\\n"
                + "| |__ | |_| |  /  | | |_| |\n"
                + "\\____/ \\__,_|_|   |_|\\___/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Curio\nWhat can i do for you?\n");
        conversation();
    }


    private static void conversation(){
        String inp = "";
        Scanner scanner = new Scanner(System.in);

        while(!CommonHelper.isClosing(inp = scanner.nextLine())) {
            if(CommonHelper.isOpening(inp)){
                CommonHelper.printMessage(MessageConstants.WELCOME);
            } else if (CommonHelper.isGet(inp)) {
                task.showList();
            } else if(CommonHelper.isUnmark(inp)){
                task.updateDoneState(Integer.parseInt(inp.replaceAll("[^0-9]", "")), false);
            } else if(CommonHelper.isMark(inp)){
                task.updateDoneState(Integer.parseInt(inp.replaceAll("[^0-9]", "")), true);
            } else if(CommonHelper.isTodo(inp)) {
                task.addItem(inp, TaskTypeEnumeration.TaskType.T);
            } else if (CommonHelper.isDeadline(inp)) {
                task.addItem(inp, TaskTypeEnumeration.TaskType.D);
            } else if (CommonHelper.isEvent(inp)) {
                task.addItem(inp, TaskTypeEnumeration.TaskType.E);
            } else {
                CommonHelper.printMessage(MessageConstants.REPEAT);
            }
        }
        CommonHelper.printMessage(MessageConstants.END);
        System.exit(1);
    }


}
