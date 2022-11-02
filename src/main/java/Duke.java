import entity.*;
import exception.EmptyActionException;
import exception.IllegalContentException;
import exception.IllegalActionException;
import manager.CommandManager;
import manager.FileManager;
import util.CommandType;
import util.PrintUtil;
import manager.TaskManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static Scanner scanner;
    public static String[] inputArr; //["action", "content"]
    public static ArrayList<String> actionList;
    public static TaskManager taskManager;
    public static FileManager fileManager;
    public static CommandManager commandManager;

    public static void getUserInput() throws IllegalActionException, EmptyActionException {
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new EmptyActionException();
        }

        inputArr = input.split(" ", 2);
        inputArr[0] = inputArr[0].toLowerCase();
        if (!CommandType.contains(inputArr[0])) {
            throw new IllegalActionException();
        }
    }

    public static void main(String[] args) {

        //greetings
        PrintUtil.printWithIndentation("Hello! I'm Duke\n \t What can I do for you?");

        //init
        fileManager = FileManager.getInstance();
        taskManager = TaskManager.getInstance();
        commandManager = CommandManager.getInstance();
        scanner = new Scanner(System.in);
        inputArr = new String[]{""};
        
        //start
        while (!inputArr[0].equals(CommandType.BYE.toString())) {

            try {
                getUserInput();
            } catch (IllegalActionException e) {
                PrintUtil.printIllegalActionMessage();
                continue;
            } catch (EmptyActionException e) {
                PrintUtil.printEmptyActionMessage();
                continue;
            }
            commandManager.executeUserInput(inputArr);
        }
    }
}
