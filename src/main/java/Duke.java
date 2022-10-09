import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.ToDo;
import exception.EmptyActionException;
import exception.IllegalContentException;
import exception.IllegalActionException;
import manager.FileManager;
import util.PrintUtil;
import manager.TaskManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static final String ACTION_LIST = "list";
    public static final String ACTION_BYE = "bye";
    public static final String ACTION_MARK = "mark";
    public static final String ACTION_UNMARK = "unmark";

    public static final String ACTION_ADD = "add";
    public static final String ACTION_TODO = "todo";
    public static final String ACTION_DEADLINE = "deadline";
    public static final String ACTION_EVENT = "event";
    public static final String ACTION_DELETE = "delete";
    public static Scanner scanner;
    public static String[] inputArr; //["action", "content"]
    public static ArrayList<String> actionList;
    public static TaskManager taskManager;
    public static FileManager fileManager;

    public static void getUserInput() throws IllegalActionException, EmptyActionException {
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new EmptyActionException();
        }

        inputArr = input.split(" ", 2);
        inputArr[0] = inputArr[0].toLowerCase();
        if (!actionList.contains(inputArr[0])) {
            throw new IllegalActionException();
        }
    }

    public static void main(String[] args) {

        //greetings
        PrintUtil.printWithIndentation("Hello! I'm Duke\n \t What can I do for you?");

        //init
        fileManager = FileManager.getInstance();
        taskManager = TaskManager.getInstance();
        scanner = new Scanner(System.in);
        inputArr = new String[]{""};
        actionList = new ArrayList<>(Arrays.asList(ACTION_LIST, ACTION_BYE, ACTION_MARK, ACTION_UNMARK, ACTION_ADD,
                ACTION_TODO, ACTION_DEADLINE, ACTION_EVENT, ACTION_DELETE));

        //start
        while (!inputArr[0].equals(ACTION_BYE)) {

            try {
                getUserInput();
            } catch (IllegalActionException e) {
                PrintUtil.printIllegalActionMessage();
                continue;
            } catch (EmptyActionException e) {
                PrintUtil.printEmptyActionMessage();
                continue;
            }

            switch (inputArr[0]) {
                case ACTION_LIST:
                    taskManager.printList();
                    break;
                case ACTION_MARK:
                case ACTION_UNMARK:
                    try {
                        taskManager.updateTaskStatus(inputArr);
                    } catch (IllegalActionException e) {
                        PrintUtil.printErrorMessage(e);
                    }
                    break;
                case ACTION_DELETE:
                    try {
                        taskManager.deleteTask(inputArr);
                    } catch (IllegalContentException e) {
                        PrintUtil.printErrorMessage(e);
                    }
                    break;
                case ACTION_BYE:
                    PrintUtil.printByeMessage();
                    break;
                default:
                    try {
                        taskManager.addTask(inputArr);
                    } catch (IllegalContentException e) {
                        PrintUtil.printErrorMessage(e);
                    }
                    break;
            }
        }
    }
}
