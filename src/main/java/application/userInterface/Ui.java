package application.userInterface;

import application.controllers.RequestController;
import application.helpers.ActionKeyword;
import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.*;
import domain.exceptions.*;

import java.util.Scanner;

public class Ui {

    private Tracker tracker;
    private Storage storage;
    private RequestController controller;


    /**
     * Default constructor for UI
     * Initialises Storage, Tracker and Request Controller
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
     * Initialise UI
     * Call intro and conversation methods
     */
    public void init(){
        intro();
        conversation();
    }

    /**
     * Display Curio Logo and welcome message
     */
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

    /**
     * Initialise scanner
     * Format input - removing keyword
     * Redirect to the respective request controller for each keyword with the expected input format
     * If throws DukeException, continue as it has been handled by the respective methods
     * Else, print Exception trace with a general error message
     */
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
                    case SNOOZE:
                        int id = CommonHelper.getNumber(inp.replaceAll("[^0-9]", "").trim());
                        controller.snooze(id, inp.replace("snooze","").replace(String.valueOf(id), "").trim());
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
