package entity;

import constant.CommandExecutor;

import java.util.Map;
import java.util.Scanner;

public class CommandParser {
    private static final Map<String, CommandExecutor> COMMAND_EXECUTOR_MAP = createMap();

    public static CommandExecutor getCommandExecutor(String inputs) {
        String commandHead = getCommandHead(inputs);
        return COMMAND_EXECUTOR_MAP.get(commandHead);
    }

    public static String getInputs() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static String getCommandHead(String inputs) {
        return inputs.split(" ", 2)[0].trim();
    }

    public static String getCommandBody(String inputs) {
        return inputs.split(" ", 2)[1].trim();
    }

    public static String[] getDeadlineDetails(String inputBody) {
        return inputBody.split("/by", 2);
    }

    public static String[] getEventDetails(String inputBody) {
        return inputBody.split("/at", 2);
    }

    public static int countCommandParts(String inputs) {
        return inputs.split(" ", 2).length;
    }

    public static Map<String, CommandExecutor> createMap() {
        return Map.of(
                "bye", CommandExecutor.BYE,
                "list", CommandExecutor.LIST,
                "mark", CommandExecutor.MARK,
                "unmark", CommandExecutor.UNMARK,
                "deadline", CommandExecutor.DEADLINE,
                "todo", CommandExecutor.TODO,
                "event", CommandExecutor.EVENT,
                "delete", CommandExecutor.DELETE,
                "save", CommandExecutor.SAVE
        );
    }

}
