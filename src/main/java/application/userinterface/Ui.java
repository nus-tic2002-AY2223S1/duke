package application.userinterface;

import application.controllers.RequestController;
import application.helpers.ActionKeyword;
import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.exceptions.DukeException;

import java.util.Scanner;

public class Ui {

    private Tracker tracker;
    private Storage storage;
    private RequestController controller;

    /**
     * Initialises User Interface with new Storage, Tracker and Request Controller.
     */
    public Ui(){
        try {
            storage = new Storage();
            tracker = new Tracker();
            controller = new RequestController(tracker, storage);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Calls introduction and starts conversation.
     */
    public void initialise(){
        introduction();
        conversation();
    }

    /**
     * Displays Curio Logo and welcome message.
     */
    private void introduction(){
        String logo =
                " _____             _   \n"
                        + "/  __ \\_   _ _  __|_| ___\n"
                        + "| |   | | | | |/ /| |/ _ \\\n"
                        + "| |__ | |_| |  /  | | |_| |\n"
                        + "\\____/ \\__,_|_|   |_|\\___/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Curio\nWhat can i do for you?\n");
    }

    /**
     * Initialises scanner and processes each request by converting to an ActionKeyword enum. For each keyword, the respective controller request is called with the expected parameters.
     * If throws DukeException, continue as error handling is already done. Else, prints Exception trace with a general error message.
     */
    private void conversation(){
        String input = "";
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                input = scanner.nextLine();
                ActionKeyword action = ActionKeyword.get(input);
                switch (action){
                    case LIST:
                        controller.list();
                        break;
                    case TODO:
                        controller.todo(input.replace(ActionKeyword.TODO.label,"").trim());
                        break;
                    case EVENT:
                        controller.event(input.replace(ActionKeyword.EVENT.label,"").trim());
                        break;
                    case DEADLINE:
                        controller.deadline(input.replace(ActionKeyword.DEADLINE.label,"").trim());
                        break;
                    case MARK:
                        controller.mark(CommonHelper.getNumber(input.replaceAll("[^-?[0-9]]", "").trim()));
                        break;
                    case UNMARK:
                        controller.unmark(CommonHelper.getNumber(input.replaceAll("[^-?[0-9]]", "").trim()));
                        break;
                    case DELETE:
                        controller.delete(CommonHelper.getNumber(input.replaceAll("[^-?[0-9]]", "").trim()));
                        break;
                    case FILTER:
                        controller.filter(input.replace(ActionKeyword.FILTER.label,"").trim());
                        break;
                    case SNOOZE:
                        boolean isDateSpecified = input.contains("/");
                        int id = CommonHelper.getNumber(input.split("/")[0].replaceAll("[^-?[0-9]]", "").trim());
                        controller.snooze(id, input.replace(ActionKeyword.SNOOZE.label,"").replace("/", "").replace(String.valueOf(id), "").trim(), isDateSpecified);
                        break;
                    case FIND:
                        controller.find(input.replace(ActionKeyword.FIND.label,"").trim());
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
