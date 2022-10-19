package Application.UserInterface;

import Application.Commands.AddTaskCommand;
import Application.Commands.DeleteTaskCommand;
import Application.Commands.MarkTaskCommand;
import Application.Commands.UnmarkTaskCommand;
import Application.Controllers.RequestController;
import Application.Helpers.ActionKeyword;
import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Aggregates.Storage.Storage;
import Domain.Aggregates.Tracker.*;
import Domain.Exceptions.*;

import java.util.Scanner;

public class Ui {

    private Tracker tracker;
    private Storage storage;
    private RequestController controller;

    public Ui(){
        try {
            storage = new Storage();
            tracker = new Tracker();
            controller = new RequestController(tracker, storage);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void init(){
        intro();
        conversation();
    }

    private void intro(){
        String logo =
                " _____             _   \n"
                        + "/  __ \\_   _ _  __|_| ___\n"
                        + "| |   | | | | |/ /| |/ _ \\\n"
                        + "| |__ | |_| |  /  | | |_| |\n"
                        + "\\____/ \\__,_|_|   |_|\\___/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Curio\nWhat can i do for you?\n");
    }
    private void conversation(){
        String inp = "";
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                inp = scanner.nextLine();
                ActionKeyword action = ActionKeyword.get(inp);
                switch (action){
                    case LIST:
                        controller.list();
                        break;
                    case TODO:
                        controller.todo(inp.replace("todo","").trim());
                        break;
                    case EVENT:
                        controller.event(inp.replace("event","").trim());
                        break;
                    case DEADLINE:
                        controller.deadline(inp.replace("deadline","").trim());
                        break;
                    case MARK:
                        controller.mark(CommonHelper.getNumber(inp.replaceAll("[^0-9]", "").trim()));
                        break;
                    case UNMARK:
                        controller.unmark(CommonHelper.getNumber(inp.replaceAll("[^0-9]", "").trim()));
                        break;
                    case DELETE:
                        controller.delete(CommonHelper.getNumber(inp.replaceAll("[^0-9]", "").trim()));
                        break;
                    case FILTER:
                        controller.filter(inp.replace("filter","").trim());
                        break;
                    case HI:
                    case HELLO:
                        controller.hello();
                        break;
                    case BYE:
                        controller.bye();
                        break;
                    default:
                        CommonHelper.printMessage(MessageConstants.REPEAT);
                        break;
                }
            } catch (DukeException ex){

            } catch (Exception ex){
                System.out.println(MessageConstants.ERROR);
                ex.printStackTrace();
            }
        }
    }

}
