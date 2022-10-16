package Application.UserInterface;

import Application.Helpers.ActionKeyword;
import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Aggregates.Tracker.Deadline;
import Domain.Aggregates.Tracker.Event;
import Domain.Aggregates.Tracker.Todo;
import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeException;
import Domain.Repositories.IStorageRepository;
import Domain.Repositories.ITaskRepository;
import Domain.Repositories.StorageRepository;

import java.io.File;
import java.util.Scanner;

public class Ui {

    private Tracker tracker;
    private File file;
    private IStorageRepository _storageRepository;

    public Ui(){
        _storageRepository = new StorageRepository();
        file = _storageRepository.init();
        tracker = new Tracker(_storageRepository.convertToTaskList());
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
    }
    private static void conversation(){
        String inp = "";
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                inp = scanner.nextLine();
                ActionKeyword action = ActionKeyword.get(inp);
                switch (action){
                    case LIST:
                        tracker.showList();
                        break;
                    case TODO:
                        tracker.addItem(new Todo(inp.replace("todo","").trim()));
                        break;
                    case EVENT:
                        tracker.addItem(new Event(inp.replace("event","").trim()));
                        break;
                    case DEADLINE:
                        tracker.addItem(new Deadline(inp.replace("deadline","").trim()));
                        break;
                    case MARK:
                        tracker.updateItem(CommonHelper.getNumber(inp.replaceAll("[^0-9]", "").trim()), true);
                        break;
                    case UNMARK:
                        tracker.updateItem(CommonHelper.getNumber(inp.replaceAll("[^0-9]", "").trim()), false);
                        break;
                    case DELETE:
                        tracker.deleteItem(CommonHelper.getNumber(inp.replaceAll("[^0-9]", "").trim()));
                        break;
                    case HI:
                    case HELLO:
                        CommonHelper.printMessage(MessageConstants.WELCOME);
                        break;
                    case BYE:
                        CommonHelper.printMessage(MessageConstants.END);
                        System.exit(1);
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
