package entity;

import constant.CommandExecutor;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class CommandParser {
    private static final Map<String, CommandExecutor> COMMAND_EXECUTOR_MAP = createMap();

    /**
     * Parse user's input to specific command executor
     *
     * @param inputs user inputs
     * @return a command executor
     */
    public static CommandExecutor getCommandExecutor(String inputs) {
        String commandHead = getCommandHead(inputs);
        return COMMAND_EXECUTOR_MAP.getOrDefault(commandHead, CommandExecutor.UNDEFINED);
    }

    /**
     * Get user input
     *
     * @return user input
     */
    public static String getInputs() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    /**
     * Get first word in user input as command executor
     *
     * @param inputs user input
     * @return first word
     */
    public static String getCommandHead(String inputs) {
        return inputs.split(" ", 2)[0].trim();
    }

    /**
     * Get the rest of the input description
     *
     * @param inputs user input
     * @return input description
     */
    public static String getCommandBody(String inputs) {
        return inputs.split(" ", 2)[1].trim();
    }

    /**
     * Get deadline's description and time
     *
     * @param inputBody input details
     * @return description and time
     */
    public static String[] getDeadlineDetails(String inputBody) {
        return Arrays.stream(inputBody.split("/by", 2)).map(String::trim).toArray(o -> new String[2]);
    }

    /**
     * Get event's description and time
     *
     * @param inputBody input details
     * @return description and time
     */
    public static String[] getEventDetails(String inputBody) {
        return Arrays.stream(inputBody.split("/at", 2)).map(String::trim).toArray(o -> new String[2]);
    }

    public static String[] getTags(String inputBody) {
        String[] details = inputBody.split("(?=#)");
        return Arrays.stream(details)
                .map(String::trim).toArray(o -> new String[details.length]);
    }

    /**
     * Used for checking for invalid input
     *
     * @param inputs user input
     * @return the number of parts of command key in by user
     */
    public static int countCommandParts(String inputs) {
        return inputs.split(" ", 2).length;
    }

    /**
     * Build relationship between command in word with command executor
     *
     * @return command executor
     */
    public static Map<String, CommandExecutor> createMap() {
        return Map.ofEntries(
                Map.entry("bye", CommandExecutor.BYE),
                Map.entry("list", CommandExecutor.LIST),
                Map.entry("mark", CommandExecutor.MARK),
                Map.entry("unmark", CommandExecutor.UNMARK),
                Map.entry("deadline", CommandExecutor.DEADLINE),
                Map.entry("todo", CommandExecutor.TODO),
                Map.entry("event", CommandExecutor.EVENT),
                Map.entry("delete", CommandExecutor.DELETE),
                Map.entry("save", CommandExecutor.SAVE),
                Map.entry("tag", CommandExecutor.TAG),
                Map.entry("find", CommandExecutor.FIND)
        );
    }
}
