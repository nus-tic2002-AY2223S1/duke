import Domain.Aggregates.Tracker.Deadline;
import Domain.Aggregates.Tracker.Event;
import Domain.Aggregates.Tracker.Todo;
import Domain.Aggregates.Tracker.Tracker;
import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeException;

import java.util.Scanner;

public class Duke {
    public static Tracker tracker = new Tracker();
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
            try {
                if (CommonHelper.isOpening(inp)) {
                    CommonHelper.printMessage(MessageConstants.WELCOME);
                } else if (CommonHelper.isGet(inp)) {
                    tracker.showList();
                } else if (CommonHelper.isUnmark(inp)) {
                    tracker.updateDoneState(Integer.parseInt(inp.replaceAll("[^0-9]", "")), false);
                } else if (CommonHelper.isMark(inp)) {
                    tracker.updateDoneState(Integer.parseInt(inp.replaceAll("[^0-9]", "")), true);
                } else if (CommonHelper.isTodo(inp)) {
                    tracker.addItem(inp, new Todo(inp.replace("todo","").trim()));
                } else if (CommonHelper.isDeadline(inp)) {
                    tracker.addItem(inp, new Deadline(inp.replace("deadline","").trim()));
                } else if (CommonHelper.isEvent(inp)) {
                    tracker.addItem(inp, new Event(inp.replace("event","").trim()));
                } else {
                    CommonHelper.printMessage(MessageConstants.REPEAT);
                }
            } catch (DukeException ex){

            } catch (Exception ex){
                System.out.println(MessageConstants.ERROR);
                ex.printStackTrace();
            }
        }
        CommonHelper.printMessage(MessageConstants.END);
        System.exit(1);
    }


}
