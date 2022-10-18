package nus.duke.parser;

import nus.duke.frontend.*;
import nus.duke.tasklist.*;
import nus.duke.exceptions.*;

public class Parser {
    private static Ui ui;

    public static String getCommand(String userInput){
        if (userInput.length() == 4){
            return userInput.substring(0, 4);
        } else {
            int idx = userInput.indexOf(" ");
            String command = userInput.substring(0, idx);
            return command;
        }
    }

    public static boolean isValidCommand(String command){
        if ((command.equals(LegalCommandEnumerations.MARK.toString())) || (command.equals(LegalCommandEnumerations.UNMARK.toString())) ||
            (command.equals(LegalCommandEnumerations.DELETE.toString())) || (command.equals(LegalCommandEnumerations.VIEW.toString())) ||
            (command.equals(LegalCommandEnumerations.EXIT.toString())) || (command.equals(LegalCommandEnumerations.TODO.toString())) ||
            (command.equals(LegalCommandEnumerations.DEADLINE.toString())) || (command.equals(LegalCommandEnumerations.EVENT.toString()))){
                return true;
        } else {
                return false;
        }

    }

    public static boolean isEmptyTask(String userInput){
        int idx = userInput.indexOf(" ");
        String str = userInput.substring(idx, userInput.length());
        if (str.isBlank()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasInputErrors(String userInput) throws WrongInputSyntaxException, EmptyTaskException {
        if (userInput.indexOf(" ") == -1){
            if (userInput.equals("VIEW") || userInput.equals("EXIT")){
                return false;
            } else {
                throw new EmptyTaskException();
            }
        }

        String command = getCommand(userInput); // e.g. "TODO buy lunch" --> "TODO" or "TODO     " --> "TODO"
        if (isValidCommand(command) == false){
            throw new WrongInputSyntaxException();
        }

        if ((isValidCommand(command) == true) && (isEmptyTask(userInput) == true)){
            throw new EmptyTaskException();
        }

        if (command.equals("DEADLINE") && (userInput.contains("/by") == false)){
            throw new WrongInputSyntaxException();
        }

        if (command.equals("EVENT") && (userInput.contains("/at") == false)){
            throw new WrongInputSyntaxException();
        }
        return false;
    }

    public static String parse(String userInput) {
        try {
            hasInputErrors(userInput);
        } catch (EmptyTaskException ete){
            System.out.println("OOPS!!! Task cannot be empty.");
        } catch (WrongInputSyntaxException wise){
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-( Please use a command from the command menu.");
            ui.printCommandMenu();
        }
        return getCommand(userInput);
    }
}
